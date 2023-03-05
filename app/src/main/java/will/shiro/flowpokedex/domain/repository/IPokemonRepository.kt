package will.shiro.flowpokedex.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import will.shiro.flowpokedex.domain.entity.Pokemon
import will.shiro.flowpokedex.domain.entity.PokemonDetail

interface IPokemonRepository {

    suspend fun getPokemons(offset: Int): Flow<PagingData<Pokemon>>
    suspend fun getPokemonDetail(id: String): PokemonDetail
}