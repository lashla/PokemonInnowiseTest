package com.lasha.pokemoninnowisetest.ui.pokeList


import android.util.Log
import androidx.lifecycle.*
import com.lasha.pokemoninnowisetest.data.entities.Pokemon
import com.lasha.pokemoninnowisetest.data.repository.RepositoryImpl
import com.lasha.pokemoninnowisetest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeListViewModel @Inject constructor(private val repositoryImpl: RepositoryImpl): ViewModel() {

    val characters = repositoryImpl.getCharacters(0,100)
}