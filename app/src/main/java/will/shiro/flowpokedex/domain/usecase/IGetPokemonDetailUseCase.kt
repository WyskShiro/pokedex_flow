package will.shiro.flowpokedex.domain.usecase

import will.shiro.flowpokedex.domain.entity.PokemonDetail
import will.shiro.flowpokedex.extensions.usecase.IUseCase

interface IGetPokemonDetailUseCase : IUseCase<String, PokemonDetail>