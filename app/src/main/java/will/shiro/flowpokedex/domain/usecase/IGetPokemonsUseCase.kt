package will.shiro.flowpokedex.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import will.shiro.flowpokedex.domain.entity.Pokemon
import will.shiro.flowpokedex.extensions.usecase.IUseCase

interface IGetPokemonsUseCase : IUseCase<Int, Flow<PagingData<Pokemon>>>