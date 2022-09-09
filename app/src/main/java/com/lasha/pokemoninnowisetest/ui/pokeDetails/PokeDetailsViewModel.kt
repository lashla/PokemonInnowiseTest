package com.lasha.pokemoninnowisetest.ui.pokeDetails

import androidx.lifecycle.*
import com.lasha.pokemoninnowisetest.data.entities.Pokemon
import com.lasha.pokemoninnowisetest.data.repository.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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