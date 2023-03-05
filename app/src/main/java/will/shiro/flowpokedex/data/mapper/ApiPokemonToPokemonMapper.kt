package will.shiro.flowpokedex.data.mapper

import javax.inject.Inject
import will.shiro.flowpokedex.data.entity.ApiPokemon
import will.shiro.flowpokedex.domain.entity.Pokemon
import will.shiro.flowpokedex.extensions.mapper.IMapper

class ApiPokemonToPokemonMapper @Inject constructor() : IMapper<ApiPokemon, Pokemon> {

    override fun mapItem(input: ApiPokemon): Pokemon {
        return Pokemon(
            name = input.name,
            url = input.url,
            id = input.url.removePrefix("https://pokeapi.co/api/v2/pokemon/").removeSuffix("/")
        )
    }
}