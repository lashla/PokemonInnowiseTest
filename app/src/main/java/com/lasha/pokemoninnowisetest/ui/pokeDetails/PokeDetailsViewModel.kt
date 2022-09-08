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

    private val _id = MutableLiveData<String>()

    private val _character = _id.switchMap { id ->
        repositoryImpl.getCharacter(id)
    }
    val character: LiveData<Resource<Pokemon>> = _character


    fun getCharacter(id: String) {
        Log.i("id", id)
        _id.value = id
    }
}