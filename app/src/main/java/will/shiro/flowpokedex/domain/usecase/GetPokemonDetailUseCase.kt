package will.shiro.flowpokedex.domain.usecase

import javax.inject.Inject
import will.shiro.flowpokedex.domain.entity.PokemonDetail
import will.shiro.flowpokedex.domain.repository.IPokemonRepository

class GetPokemonDetailUseCase @Inject constructor(
    private val repository: IPokemonRepository
) : IGetPokemonDetailUseCase {

    override suspend fun invoke(input: String): PokemonDetail {
        return repository.getPokemonDetail(input)
    }
}