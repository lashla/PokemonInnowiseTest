package com.lasha.pokemoninnowisetest.domain.di

import android.content.Context
import com.lasha.pokemoninnowisetest.data.repository.RepositoryImpl
import com.lasha.pokemoninnowisetest.domain.db.PokemonDao
import com.lasha.pokemoninnowisetest.data.remote.PokemonRemoteDataSource
import com.lasha.pokemoninnowisetest.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: PokemonRemoteDataSource, localDataSource: PokemonDao, context: Context): Repository {
        return RepositoryImpl(remoteDataSource, localDataSource, context)
    }
}