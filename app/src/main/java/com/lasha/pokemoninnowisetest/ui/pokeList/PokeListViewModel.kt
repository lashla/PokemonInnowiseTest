package com.lasha.pokemoninnowisetest.ui.pokeList

import androidx.lifecycle.*
import com.lasha.pokemoninnowisetest.data.repository.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeListViewModel @Inject constructor(private val repositoryImpl: RepositoryImpl): ViewModel() {

    var charactersData = repositoryImpl.getCharacters(0,20)

    fun retrieveData(offset:Int, limit:Int) {
        viewModelScope.launch(Dispatchers.Main) {
            charactersData = repositoryImpl.getCharacters(offset,limit)
        }
    }
}