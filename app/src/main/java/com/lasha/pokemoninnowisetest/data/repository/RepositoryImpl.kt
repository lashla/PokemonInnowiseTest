package com.lasha.pokemoninnowisetest.data.repository

import android.util.Log
import com.lasha.pokemoninnowisetest.data.entities.DbEntity
import com.lasha.pokemoninnowisetest.domain.db.PokemonDao
import com.lasha.pokemoninnowisetest.data.remote.PokemonRemoteDataSource
import com.lasha.pokemoninnowisetest.domain.repository.Repository
import com.lasha.pokemoninnowisetest.utils.performGetOperation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(private val remoteDataSource: PokemonRemoteDataSource,
                                         private val localDataSource: PokemonDao): Repository {

    override fun getCharacter(id: String)= performGetOperation(
        databaseQuery = { localDataSource.getCharacter(id) },
        networkCall = { withContext(Dispatchers.IO){remoteDataSource.getCharacter(id)} },
        saveCallResult = { withContext(Dispatchers.IO){localDataSource.insert(DbEntity(it.id, it.name, it.weight, it.height, it.types[0].type.name, it.sprites.frontDefault))}
        Log.i("eee", it.id.toString() + it.name + it.weight + it.height + it.sprites.toString())}
    )

    override fun getCharacters() = performGetOperation(
        databaseQuery = { localDataSource.getAllCharacters() },
        networkCall = { withContext(Dispatchers.IO){remoteDataSource.getCharacters()} },
        saveCallResult = {
            withContext(Dispatchers.IO) {
                for (element in it.results) {
                    Log.i(
                        "results",
                        "/-?[0-9]+/$".toRegex()
                            .find(element.url)!!.value.filter { item -> item.isDigit() || item == '-' } + element.name)
                    localDataSource.insert(
                        DbEntity(
                            "/-?[0-9]+/$".toRegex()
                                .find(element.url)!!.value.filter { item -> item.isDigit() || item == '-' }
                                .toInt(),
                            element.name, null, null, null, null
                        )
                    )
                }
            }
        }
    )



}