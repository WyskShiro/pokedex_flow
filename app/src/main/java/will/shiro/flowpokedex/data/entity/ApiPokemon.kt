package will.shiro.flowpokedex.data.entity

import com.squareup.moshi.Json

data class ApiPokemon(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "url") val url: String
)