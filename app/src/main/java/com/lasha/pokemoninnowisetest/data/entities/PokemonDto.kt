package com.lasha.pokemoninnowisetest.data.entities

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class PokemonDto(
    @PrimaryKey
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<PokemonType>,
    val sprites: PokemonSprites
)

data class PokemonSprites(
    @SerializedName("front_default")
    val frontDefault: String?,
)

data class PokemonType(
    val slot: Int,
    val type: NamedApiResource
)
