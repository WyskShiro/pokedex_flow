package will.shiro.flowpokedex.domain.usecase

import androidx.paging.PagingData
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import will.shiro.flowpokedex.data.repository.PokemonRepository
import will.shiro.flowpokedex.domain.entity.Pokemon

class GetPokemonsUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : IGetPokemonsUseCase {

    override suspend fun invoke(input: Int): Flow<PagingData<Pokemon>> {
        return pokemonRepository.getPokemons(input)
    }
}