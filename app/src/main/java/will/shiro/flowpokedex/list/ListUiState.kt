package will.shiro.flowpokedex.list

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.paging.PagingData
import will.shiro.flowpokedex.domain.entity.Pokemon

data class ListUiState constructor(
    val showLoading: Boolean = true,
    val list: PagingData<Pokemon>? = null
)

sealed class ListUiIntent {
    data class LoadPokemons(val offset: Int) : ListUiIntent()
    data class NavigateToDetail(val id: String) : ListUiIntent()
}

sealed class ListUiEvent {
    data class ShowError(val error: String) : ListUiEvent()
    data class Navigate(@IdRes val direction: Int, val bundle: Bundle? = null) : ListUiEvent()
}