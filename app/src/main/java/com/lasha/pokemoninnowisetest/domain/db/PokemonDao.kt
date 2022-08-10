package com.lasha.pokemoninnowisetest.domain.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lasha.pokemoninnowisetest.data.entities.Pokemon
import com.lasha.pokemoninnowisetest.utils.Resource
import javax.sql.DataSource

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon")
    fun getAllCharacters(): LiveData<List<Pokemon>>

    @Query("SELECT * FROM pokemon WHERE name = :id")
    fun getCharacter(id: String): LiveData<Pokemon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<Pokemon>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: Pokemon)

    @Update(entity = Pokemon::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(character: Pokemon)

}