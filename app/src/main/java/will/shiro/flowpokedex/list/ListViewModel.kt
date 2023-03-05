package will.shiro.flowpokedex.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import will.shiro.flowpokedex.R
import will.shiro.flowpokedex.domain.usecase.GetPokemonsUseCase

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ListUiState())
    private val _uiEvent = MutableSharedFlow<ListUiEvent>()

    val uiState: StateFlow<ListUiState> = _uiState
    val uiEvent: SharedFlow<ListUiEvent> = _uiEvent

    fun setUpData() {
        viewModelScope.launch {
            triggerUiIntent(ListUiIntent.LoadPokemons(offset = 0))
        }
    }

    fun triggerUiIntent(intent: ListUiIntent) {
        when (intent) {
            is ListUiIntent.LoadPokemons -> loadPokemons(intent.offset)
            is ListUiIntent.NavigateToDetail -> navigateToDetail(intent.id)
        }
    }

    private fun navigateToDetail(id: String) {
        val args = ListFragmentDirections.actionListToDetail(id).arguments
        viewModelScope.launch {
            _uiEvent.emit(ListUiEvent.Navigate(R.id.actionListToDetail, args))
        }
    }

    private fun loadPokemons(offset: Int) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(showLoading = true) }
                getPokemonsUseCase(offset)
                    .cachedIn(this)
                    .collect { pagingData ->
                        _uiState.update { it.copy(list = pagingData, showLoading = false) }
                    }
            } catch (e: Exception) {
                _uiEvent.emit(ListUiEvent.ShowError(e.message.toString()))
            }
        }
    }
}