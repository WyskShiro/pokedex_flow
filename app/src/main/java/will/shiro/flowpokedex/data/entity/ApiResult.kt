package will.shiro.flowpokedex.data.entity

import com.squareup.moshi.Json

data class ApiResult<T>(
    @field:Json(name = "results") val results: T
)