package com.lasha.pokemoninnowisetest.domain.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lasha.pokemoninnowisetest.data.entities.DbEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemons")
    fun getAllCharacters(): LiveData<List<DbEntity>>

    @Query("SELECT * FROM pokemons WHERE name = :id")
    fun getCharacter(id: String): LiveData<DbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<DbEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: DbEntity)

    @Update(entity = DbEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(character: DbEntity)

}