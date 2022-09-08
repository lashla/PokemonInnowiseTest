package com.lasha.pokemoninnowisetest.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class Pokemon(
    @PrimaryKey @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "weight") var weight: Int?,
    @ColumnInfo(name = "height") var height: Int?,
    @ColumnInfo(name = "type") var type: String?,
    @ColumnInfo(name = "image") var image: String?,
)
