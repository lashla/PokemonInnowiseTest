package com.lasha.pokemoninnowisetest.domain.repository

import com.lasha.pokemoninnowisetest.data.entities.Pokemon
import javax.inject.Singleton

@Singleton
interface Repository {
    suspend fun getCharacters(offset: Int, limit: Int): List<Pokemon>
    suspend fun getCharacter(name: String): Pokemon
}