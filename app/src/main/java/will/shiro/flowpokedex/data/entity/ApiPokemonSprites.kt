package will.shiro.flowpokedex.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiPokemonSprites constructor(
    @Json(name = "back_default") val back: String? = null,
    @Json(name = "back_shiny") val backShiny: String? = null,
    @Json(name = "front_default") val front: String? = null,
    @Json(name = "front_shiny") val frontShiny: String? = null
)