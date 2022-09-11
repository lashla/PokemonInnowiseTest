package com.lasha.pokemoninnowisetest.ui.pokeDetails

import androidx.lifecycle.*
import com.lasha.pokemoninnowisetest.data.entities.Pokemon
import com.lasha.pokemoninnowisetest.domain.useCases.GetPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeDetailViewModel @Inject constructor(private val getPokemonUseCase: GetPokemonUseCase): ViewModel() {

    val character = MutableLiveData<Pokemon>()

    fun getPokemon(name: String){
        viewModelScope.launch {
            character.postValue(getPokemonUseCase.invoke(name))
        }
    }
}