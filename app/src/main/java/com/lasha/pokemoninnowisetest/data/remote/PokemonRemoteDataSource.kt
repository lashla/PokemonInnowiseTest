package com.lasha.pokemoninnowisetest.data.remote

import javax.inject.Inject


class PokemonRemoteDataSource @Inject constructor(private val pokemonService: PokemonService): BaseDataSource() {

    suspend fun getCharacters() = getResult { pokemonService.getPokemonList(10,40) }
    suspend fun getCharacter(id: String) = getResult { pokemonService.getPokemonInfo(id) }
}