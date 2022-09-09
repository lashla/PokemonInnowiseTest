package com.lasha.pokemoninnowisetest.ui.pokeDetails

import android.util.Log
import androidx.lifecycle.*
import com.lasha.pokemoninnowisetest.data.entities.Pokemon
import com.lasha.pokemoninnowisetest.data.repository.RepositoryImpl
import com.lasha.pokemoninnowisetest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokeDetailViewModel @Inject constructor(private val repositoryImpl: RepositoryImpl): ViewModel() {

    val character = MutableLiveData<Pokemon>()

    fun getPokemon(name: String){
        viewModelScope.launch {
            character.postValue(repositoryImpl.getCharacter(name))
        }
    }
}