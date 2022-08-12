package com.lasha.pokemoninnowisetest.ui.pokeList

import androidx.lifecycle.*
import com.lasha.pokemoninnowisetest.data.repository.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokeListViewModel @Inject constructor(private val repositoryImpl: RepositoryImpl): ViewModel() {

    val characters = repositoryImpl.getCharacters(0,100)
}