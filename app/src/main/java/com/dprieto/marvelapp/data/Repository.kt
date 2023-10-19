package com.dprieto.marvelapp.data

import com.dprieto.marvelapp.domain.MarvelCharacter
import com.dprieto.marvelapp.domain.MarvelMedia
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getCharacters(): Flow<List<MarvelCharacter>>
    suspend fun getCharacterSeries(characterId: Int): Flow<List<MarvelMedia>>
    suspend fun getCharacterComics(characterId: Int): Flow<List<MarvelMedia>>
    suspend fun setFavorite(character: MarvelCharacter)

}
