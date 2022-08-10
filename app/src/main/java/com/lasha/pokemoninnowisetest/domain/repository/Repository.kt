package com.lasha.pokemoninnowisetest.domain.repository

import androidx.lifecycle.LiveData
import com.lasha.pokemoninnowisetest.data.entities.Pokemon
import com.lasha.pokemoninnowisetest.utils.Resource
import javax.inject.Singleton

@Singleton
interface Repository {
    fun getCharacters(offset: Int, limit: Int): LiveData<Resource<List<Pokemon>>>
    fun getCharacter(id: String): LiveData<Resource<Pokemon>>
}