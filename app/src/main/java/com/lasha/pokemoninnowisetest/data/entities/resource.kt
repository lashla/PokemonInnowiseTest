package com.lasha.pokemoninnowisetest.data.entities

import com.google.gson.annotations.SerializedName

data class NamedApiResource(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

data class NamedApiResourceList(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<NamedApiResource>
)
