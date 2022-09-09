package com.lasha.pokemoninnowisetest.data.repository

import android.content.Context
import android.util.Log
import com.lasha.pokemoninnowisetest.data.entities.Pokemon
import com.lasha.pokemoninnowisetest.domain.db.PokemonDao
import com.lasha.pokemoninnowisetest.data.remote.PokemonRemoteDataSource
import com.lasha.pokemoninnowisetest.domain.repository.Repository
import com.lasha.pokemoninnowisetest.utils.CheckInternetConnection
import com.lasha.pokemoninnowisetest.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(private val remoteDataSource: PokemonRemoteDataSource,
                                         private val localDataSource: PokemonDao,
                                         private val context: Context): Repository {

    override suspend fun getCharacter(id: String): Pokemon {
        var returnData = Pokemon(id.toInt(), null, null, null, null, null)
        if (CheckInternetConnection.connectivityStatus(context)) {
            val character = remoteDataSource.getCharacter(id)
                if(character.status == Resource.Status.SUCCESS){
                    if (character.data != null){
                    localDataSource.insert(
                        Pokemon(
                            character.data.id,
                            character.data.name,
                            character.data.weight,
                            character.data.height,
                            character.data.types[0].type.name,
                            character.data.sprites.frontDefault
                        )
                    )
                        returnData = Pokemon(
                            character.data.id,
                            character.data.name,
                            character.data.weight,
                            character.data.height,
                            character.data.types[0].type.name,
                            character.data.sprites.frontDefault
                        )
                        Log.i("FromRemote", "returns")

                    }
                }

        } else {
            returnData = localDataSource.getCharacter(id)
            Log.i("FromLocal", "returns")
            localDataSource.insert(returnData)
        }
        return returnData
    }

    override suspend fun getCharacters(offset: Int, limit: Int): List<Pokemon> {
        var returnData = ArrayList<Pokemon>()
        if (CheckInternetConnection.connectivityStatus(context)) {
            Log.i("FromRemote", "returns")
            val character = withContext(Dispatchers.IO) { remoteDataSource.getCharacters(offset, limit) }
            withContext(Dispatchers.IO) {
                if(character.status == Resource.Status.SUCCESS){
                    if (character.data != null){
                        for (elements in character.data.results){
                            returnData.add(Pokemon(
                                "/-?[0-9]+/$".toRegex()
                                .find(elements.url)!!.value.filter { item -> item.isDigit() || item == '-' }
                                .toInt(),
                                elements.name,
                                null, null, null, null
                            ))
                        }
                        localDataSource.insertAll(returnData)
                    }
                }
            }
        } else {
            returnData = withContext(Dispatchers.IO) { localDataSource.getAllCharacters() } as ArrayList<Pokemon>
            localDataSource.insertAll(returnData)
            Log.i("FromLocal", "returns")
        }
        return returnData
    }
}