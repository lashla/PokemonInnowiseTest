package com.lasha.pokemoninnowisetest.domain.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lasha.pokemoninnowisetest.data.entities.DbEntity

@Database(entities = [DbEntity::class], version = 1, exportSchema = false)
abstract class PokeDatabase: RoomDatabase()  {

    abstract fun pokemonDao(): PokemonDao

    companion object {
        const val databaseName = "PokemonDB"
    }

}