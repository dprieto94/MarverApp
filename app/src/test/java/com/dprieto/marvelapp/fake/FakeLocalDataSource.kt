package com.dprieto.marvelapp.fake

import com.dprieto.marvelapp.data.local.LocalDataSource
import com.dprieto.marvelapp.data.local.models.MarvelHeroLocal
import com.dprieto.marvelapp.utils.generateHerosLocal
import kotlinx.coroutines.flow.Flow

class FakeLocalDataSource: LocalDataSource {
    override fun getCharacters(): Flow<List<MarvelHeroLocal>> {
        return generateHerosLocal()
    }

    override fun insertCharacters(localCharacterList: List<MarvelHeroLocal>) {

    }

    override fun updateCharacter(hero: MarvelHeroLocal) {

    }

    override fun existCharacters(): Boolean {
        return false
    }
}