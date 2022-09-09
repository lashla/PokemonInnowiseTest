package com.lasha.pokemoninnowisetest.domain.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lasha.pokemoninnowisetest.data.entities.Pokemon

@Database(entities = [Pokemon::class], version = 4, exportSchema = false)
abstract class PokeDatabase: RoomDatabase()  {

    abstract fun pokemonDao(): PokemonDao

    companion object {
        const val databaseName = "PokemonDB"
    }
}