package com.lasha.pokemoninnowisetest.data.remote

import com.lasha.pokemoninnowisetest.data.entities.NamedApiResourceList
import com.lasha.pokemoninnowisetest.data.entities.Pokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon/")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<NamedApiResourceList>

    @GET("pokemon/{id}/")
    suspend fun getPokemonInfo(@Path("id") id: String): Response<Pokemon>
}