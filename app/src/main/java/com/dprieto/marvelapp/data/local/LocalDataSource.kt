package com.dprieto.marvelapp.data.local

import com.dprieto.marvelapp.data.local.models.MarvelHeroLocal
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getCharacters(): Flow<List<MarvelHeroLocal>>
    fun insertCharacters(localCharacterList: List<MarvelHeroLocal>)
    fun updateCharacter(hero: MarvelHeroLocal)
    fun existCharacters(): Boolean

}
