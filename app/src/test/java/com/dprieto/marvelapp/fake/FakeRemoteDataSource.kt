package com.dprieto.marvelapp.fake

import com.dprieto.marvelapp.data.remote.RemoteDataSource
import com.dprieto.marvelapp.data.remote.models.MarvelResponse
import com.dprieto.marvelapp.utils.generateCharacters
import com.dprieto.marvelapp.utils.generateComics
import com.dprieto.marvelapp.utils.generateSeries
import kotlinx.coroutines.flow.Flow

class FakeRemoteDataSource(): RemoteDataSource {
    override suspend fun getCharacters(): MarvelResponse {
        return generateCharacters()
    }

    override suspend fun getCharacterSeries(characterId: Int): Flow<MarvelResponse> {
        return generateSeries()
    }

    override suspend fun getCharacterComics(characterId: Int): Flow<MarvelResponse> {
        return generateComics()
    }
}