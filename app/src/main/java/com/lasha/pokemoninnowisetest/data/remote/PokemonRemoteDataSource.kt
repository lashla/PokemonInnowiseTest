package com.lasha.pokemoninnowisetest.data.remote

import javax.inject.Inject


class PokemonRemoteDataSource @Inject constructor(private val pokemonService: PokemonService): BaseDataSource() {
    suspend fun getCharacters(offset: Int = 0, limit: Int = 20) = getResult { pokemonService.getPokemonList(offset , limit) }
    suspend fun getCharacter(id: String) = getResult { pokemonService.getPokemonInfo(id) }
}