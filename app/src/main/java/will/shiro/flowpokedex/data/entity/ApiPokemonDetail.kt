package will.shiro.flowpokedex.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiPokemonDetail constructor(
    @field:Json(name = "name") val name: String? = null,
    @field:Json(name = "sprites") val sprites: ApiPokemonSprites? = null,
    @field:Json(name = "abilities") val abilities: List<ApiAbility>? = null
)