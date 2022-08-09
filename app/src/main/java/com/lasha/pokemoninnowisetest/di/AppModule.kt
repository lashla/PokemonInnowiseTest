package com.lasha.pokemoninnowisetest.domain.di

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

    @Provides
    @Singleton
    fun provideDao(pokemonDatabase: PokeDatabase): PokemonDao{
        return pokemonDatabase.pokemonDao()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

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