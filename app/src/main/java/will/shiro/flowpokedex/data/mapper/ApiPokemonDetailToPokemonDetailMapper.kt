package will.shiro.flowpokedex.data.mapper

import javax.inject.Inject
import will.shiro.flowpokedex.data.entity.ApiPokemonDetail
import will.shiro.flowpokedex.data.entity.ApiPokemonSprites
import will.shiro.flowpokedex.domain.entity.PokemonDetail
import will.shiro.flowpokedex.domain.entity.PokemonSprites
import will.shiro.flowpokedex.extensions.mapper.IMapper

class ApiPokemonDetailToPokemonDetailMapper @Inject constructor(
    private val apiSpritesMapper: IMapper<ApiPokemonSprites, PokemonSprites>
) : IMapper<ApiPokemonDetail, PokemonDetail> {

    override fun mapItem(input: ApiPokemonDetail): PokemonDetail {
        if (input.name == null || input.sprites == null) throw InvalidPokemonDetailException()

        return PokemonDetail(
            name = input.name,
            sprites = apiSpritesMapper.mapItem(input.sprites)
        )
    }
}

class InvalidPokemonDetailException : Exception()