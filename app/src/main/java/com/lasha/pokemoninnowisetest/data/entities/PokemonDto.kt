package com.lasha.pokemoninnowisetest.data.entities

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class PokemonDto(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("types")
    val types: List<PokemonType>,
    @SerializedName("sprites")
    val sprites: PokemonSprites
)

data class PokemonSprites(
    @SerializedName("front_default")
    val frontDefault: String?,
)

data class PokemonType(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: NamedApiResource
)
