package will.shiro.flowpokedex.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiAbility constructor(
    @Json(name = "is_hidden") val isHidden: Boolean? = null
)