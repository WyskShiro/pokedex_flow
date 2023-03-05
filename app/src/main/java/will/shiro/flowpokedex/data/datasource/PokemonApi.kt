package will.shiro.flowpokedex.data.datasource

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import will.shiro.flowpokedex.data.entity.ApiPokemon
import will.shiro.flowpokedex.data.entity.ApiPokemonDetail
import will.shiro.flowpokedex.data.entity.ApiResult

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemons(@Query("offset") offset: Int = 0): ApiResult<List<ApiPokemon>>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: String): ApiPokemonDetail
}