package com.lasha.pokemoninnowisetest.domain.repository

import androidx.lifecycle.LiveData
import com.lasha.pokemoninnowisetest.data.entities.Pokemon
import com.lasha.pokemoninnowisetest.utils.Resource
import javax.inject.Singleton

@Singleton
interface Repository {
    suspend fun getCharacters(offset: Int, limit: Int): List<Pokemon>
    suspend fun getCharacter(id: String): Pokemon
}