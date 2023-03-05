package will.shiro.flowpokedex.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import javax.inject.Inject
import will.shiro.flowpokedex.data.datasource.PokemonApi
import will.shiro.flowpokedex.data.entity.ApiPokemon

class PokemonPagingSource @Inject constructor(
    private val pokemonApi: PokemonApi
) : PagingSource<Int, ApiPokemon>() {

    override fun getRefreshKey(state: PagingState<Int, ApiPokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiPokemon> {
        val page = (params.key ?: 0)
        val offset = page * 20

        return try {
            val response = pokemonApi.getPokemons(offset)
            val prevKey = if (page == 0) null else page - 1
            val nextKey = if (response.results.isEmpty()) null else page + 1

            LoadResult.Page(
                data = response.results,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}