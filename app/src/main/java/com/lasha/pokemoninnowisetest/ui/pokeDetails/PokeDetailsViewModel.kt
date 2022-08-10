package com.lasha.pokemoninnowisetest.ui.pokeDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.lasha.pokemoninnowisetest.data.entities.Pokemon
import com.lasha.pokemoninnowisetest.data.repository.RepositoryImpl
import com.lasha.pokemoninnowisetest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokeDetailViewModel @Inject constructor(private val repositoryImpl: RepositoryImpl): ViewModel() {

    private val _id = MutableLiveData<String>()

    private val _character = _id.switchMap { id ->
        repositoryImpl.getCharacter(id)
    }
    val character: LiveData<Resource<Pokemon>> = _character


    fun start(id: String) {
        Log.i("id", id)
        _id.value = id
    }
}