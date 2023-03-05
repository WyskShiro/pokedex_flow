package will.shiro.flowpokedex.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import will.shiro.flowpokedex.data.entity.ApiPokemon
import will.shiro.flowpokedex.data.entity.ApiPokemonDetail
import will.shiro.flowpokedex.data.entity.ApiPokemonSprites
import will.shiro.flowpokedex.data.mapper.ApiPokemonDetailToPokemonDetailMapper
import will.shiro.flowpokedex.data.mapper.ApiPokemonSpritesToPokemonSpritesMapper
import will.shiro.flowpokedex.data.mapper.ApiPokemonToPokemonMapper
import will.shiro.flowpokedex.data.repository.PokemonRepository
import will.shiro.flowpokedex.detail.DetailFragment
import will.shiro.flowpokedex.detail.DetailFragmentArgs
import will.shiro.flowpokedex.domain.entity.Pokemon
import will.shiro.flowpokedex.domain.entity.PokemonDetail
import will.shiro.flowpokedex.domain.entity.PokemonSprites
import will.shiro.flowpokedex.domain.repository.IPokemonRepository
import will.shiro.flowpokedex.domain.usecase.GetPokemonDetailUseCase
import will.shiro.flowpokedex.domain.usecase.GetPokemonsUseCase
import will.shiro.flowpokedex.domain.usecase.IGetPokemonDetailUseCase
import will.shiro.flowpokedex.domain.usecase.IGetPokemonsUseCase
import will.shiro.flowpokedex.extensions.mapper.IMapper

@Module
@InstallIn(ActivityRetainedComponent::class)
interface PokemonModule {

    // Data
    @Binds
    fun bindsApiPokemonToPokemonMapper(
        mapper: ApiPokemonToPokemonMapper
    ): IMapper<ApiPokemon, Pokemon>

    @Binds
    fun bindsApiPokemonSpritesToPokemonSpritesMapper(
        mapper: ApiPokemonSpritesToPokemonSpritesMapper
    ): IMapper<ApiPokemonSprites, PokemonSprites>

    @Binds
    fun bindsApiPokemonDetailToPokemonDetailMapper(
        mapper: ApiPokemonDetailToPokemonDetailMapper
    ): IMapper<ApiPokemonDetail, PokemonDetail>

    @Binds
    fun bindsPokemonRepository(
        repository: PokemonRepository
    ): IPokemonRepository

    // Domain
    @Binds
    fun bindsGetPokemonsUseCase(
        getPokemonsUseCase: GetPokemonsUseCase
    ): IGetPokemonsUseCase

    @Binds
    fun bindsGetPokemonDetailUseCase(
        getPokemonDetailUseCase: GetPokemonDetailUseCase
    ): IGetPokemonDetailUseCase
}

