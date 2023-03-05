package will.shiro.flowpokedex.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import will.shiro.flowpokedex.BuildConfig
import will.shiro.flowpokedex.data.datasource.PokemonApi

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providesPokemonApi(okHttpClient: OkHttpClient, moshi: Moshi): PokemonApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(PokemonApi::class.java)
    }

    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG)
                        HttpLoggingInterceptor.Level.BODY
                    else
                        HttpLoggingInterceptor.Level.NONE
                }
            )
            .build()
    }

    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }
}