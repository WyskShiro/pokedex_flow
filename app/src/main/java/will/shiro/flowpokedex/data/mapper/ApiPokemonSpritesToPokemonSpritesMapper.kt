package will.shiro.flowpokedex.data.mapper

import javax.inject.Inject
import will.shiro.flowpokedex.data.entity.ApiPokemonSprites
import will.shiro.flowpokedex.domain.entity.PokemonSprites
import will.shiro.flowpokedex.extensions.mapper.IMapper

class ApiPokemonSpritesToPokemonSpritesMapper @Inject constructor() :
    IMapper<ApiPokemonSprites, PokemonSprites> {

    override fun mapItem(input: ApiPokemonSprites): PokemonSprites {
        if (input.back == null || input.front == null) throw InvalidPokemonSpritesException()

        return PokemonSprites(
            back = input.back,
            backShiny = input.backShiny ?: "",
            front = input.front,
            frontShiny = input.frontShiny ?: ""
        )
    }
}

class InvalidPokemonSpritesException : Exception()