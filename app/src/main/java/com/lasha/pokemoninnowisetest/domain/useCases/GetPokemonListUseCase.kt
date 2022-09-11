package com.lasha.pokemoninnowisetest.domain.useCases

import com.lasha.pokemoninnowisetest.domain.repository.Repository
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(offset: Int, limit: Int) = repository.getCharacters(offset, limit)
}