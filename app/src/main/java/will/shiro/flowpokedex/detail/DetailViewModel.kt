package will.shiro.flowpokedex.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import will.shiro.flowpokedex.domain.usecase.GetPokemonDetailUseCase

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    private val _uiEvent = MutableSharedFlow<DetailUiEvent>()

    val uiState: StateFlow<DetailUiState> = _uiState
    val uiEvent: SharedFlow<DetailUiEvent> = _uiEvent

    fun setUpData() {
        stateHandle.get<String>("id")?.let { id ->
            triggerUiIntent(DetailUiIntent.LoadPokemonDetail(id))
        }
    }

    fun triggerUiIntent(detailUiIntent: DetailUiIntent) {
        when (detailUiIntent) {
            is DetailUiIntent.LoadPokemonDetail -> loadPokemonDetail(detailUiIntent.id)
        }
    }

    private fun loadPokemonDetail(id: String) {
        viewModelScope.launch {
            try {
                val pokemonDetail = getPokemonDetailUseCase(id)
                _uiState.update { it.copy(showLoading = false, pokemonDetail = pokemonDetail) }
            } catch (e: Exception) {
                _uiEvent.emit(DetailUiEvent.ShowError(e.message.toString()))
                e.printStackTrace()
            }
        }
    }
}