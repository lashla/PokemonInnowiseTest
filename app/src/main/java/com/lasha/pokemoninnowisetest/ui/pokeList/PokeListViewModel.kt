package com.lasha.pokemoninnowisetest.ui.pokeList

import androidx.lifecycle.*
import com.lasha.pokemoninnowisetest.data.entities.Pokemon
import com.lasha.pokemoninnowisetest.domain.useCases.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeListViewModel @Inject constructor(private val getPokemonListUseCase: GetPokemonListUseCase): ViewModel() {

    val charactersData = MutableLiveData<List<Pokemon>>()

    init {
        retrieveData(offset = 0, 20)
    }

    fun retrieveData(offset:Int, limit:Int) {
        viewModelScope.launch(Dispatchers.Main) {
            charactersData.postValue(getPokemonListUseCase.invoke(offset, limit))
        }
    }
}