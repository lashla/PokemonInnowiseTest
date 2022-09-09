package com.lasha.pokemoninnowisetest.domain.db

import androidx.room.*
import com.lasha.pokemoninnowisetest.data.entities.Pokemon

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon")
    suspend fun getAllCharacters(): List<Pokemon>

    @Query("SELECT * FROM pokemon WHERE name = :id")
    suspend fun getCharacter(id: String): Pokemon

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<Pokemon>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: Pokemon)

    @Update(entity = Pokemon::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(character: Pokemon)

}