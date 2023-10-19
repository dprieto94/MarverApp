package com.dprieto.marvelapp.data.remote

import com.dprieto.marvelapp.data.remote.models.MarvelResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    suspend fun getCharacters(): MarvelResponse

    suspend fun getCharacterSeries(characterId: Int): Flow<MarvelResponse>

    suspend fun getCharacterComics(characterId: Int): Flow<MarvelResponse>

}
