package com.dprieto.marvelapp.data.remote

import com.dprieto.marvelapp.data.remote.models.MarvelResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val api: MarvelApi): RemoteDataSource {
    override suspend fun getCharacters(): MarvelResponse {
        return api.getCharacters()
    }

    override suspend fun getCharacterSeries(characterId: Int): Flow<MarvelResponse> {
        return flow { emit(api.getSeries(characterId)) }
    }

    override suspend fun getCharacterComics(characterId: Int): Flow<MarvelResponse> {
        return flow { emit(api.getComics(characterId)) }
    }

}