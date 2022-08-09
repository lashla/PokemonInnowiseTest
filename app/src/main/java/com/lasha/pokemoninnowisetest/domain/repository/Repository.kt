package com.lasha.pokemoninnowisetest.domain.repository

import androidx.lifecycle.LiveData
import com.lasha.pokemoninnowisetest.data.entities.DbEntity
import com.lasha.pokemoninnowisetest.utils.Resource
import javax.inject.Singleton

@Singleton
interface Repository {
    fun getCharacters(): LiveData<Resource<List<DbEntity>>>
    fun getCharacter(id: String): LiveData<Resource<DbEntity>>
}