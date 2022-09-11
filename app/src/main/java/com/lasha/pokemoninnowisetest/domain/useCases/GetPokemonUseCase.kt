package com.lasha.pokemoninnowisetest.domain.useCases

import com.lasha.pokemoninnowisetest.domain.repository.Repository
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(name: String) = repository.getCharacter(name)
}