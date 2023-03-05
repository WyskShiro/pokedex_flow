package will.shiro.flowpokedex.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import will.shiro.flowpokedex.data.datasource.PokemonApi
import will.shiro.flowpokedex.data.entity.ApiPokemon
import will.shiro.flowpokedex.data.entity.ApiPokemonDetail
import will.shiro.flowpokedex.data.paging.PokemonPagingSource
import will.shiro.flowpokedex.domain.entity.Pokemon
import will.shiro.flowpokedex.domain.entity.PokemonDetail
import will.shiro.flowpokedex.domain.repository.IPokemonRepository
import will.shiro.flowpokedex.extensions.mapper.IMapper

class PokemonRepository @Inject constructor(
    private val pokemonApi: PokemonApi,
    private val pokemonPagingSource: PokemonPagingSource,
    private val apiPokemonMapper: IMapper<ApiPokemon, Pokemon>,
    private val apiPokemonDetailMapper: IMapper<ApiPokemonDetail, PokemonDetail>
) : IPokemonRepository {

    override suspend fun getPokemons(offset: Int): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { pokemonPagingSource }
        ).flow.map { pagingData ->
            pagingData.map { apiPokemonMapper.mapItem(it) }
        }
    }

    override suspend fun getPokemonDetail(id: String): PokemonDetail {
        val apiPokemonDetail = pokemonApi.getPokemonDetail(id)
        return apiPokemonDetailMapper.mapItem(apiPokemonDetail)
    }
}