package will.shiro.flowpokedex.detail

import will.shiro.flowpokedex.domain.entity.PokemonDetail

data class DetailUiState constructor(
    val showLoading: Boolean = true,
    val pokemonDetail: PokemonDetail? = null
)

sealed class DetailUiIntent {
    data class LoadPokemonDetail(val id: String) : DetailUiIntent()
}

sealed class DetailUiEvent {
    data class ShowError(val error: String) : DetailUiEvent()
}