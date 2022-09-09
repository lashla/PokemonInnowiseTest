package com.lasha.pokemoninnowisetest.domain.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lasha.pokemoninnowisetest.domain.db.PokeDatabase
import com.lasha.pokemoninnowisetest.domain.db.PokemonDao
import com.lasha.pokemoninnowisetest.data.remote.PokemonRemoteDataSource
import com.lasha.pokemoninnowisetest.data.remote.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PokeDatabase{
        return Room.databaseBuilder(
            context,
            PokeDatabase::class.java,
            PokeDatabase.databaseName,
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideDao(pokemonDatabase: PokeDatabase): PokemonDao{
        return pokemonDatabase.pokemonDao()
    }



    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit {
        val okHttpClient by lazy {
            OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).build()
        }
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): PokemonService = retrofit.create(PokemonService::class.java)

    @Provides
    @Singleton
    fun providePokemonRemoteDataSource(pokemonService: PokemonService) = PokemonRemoteDataSource(pokemonService)

}