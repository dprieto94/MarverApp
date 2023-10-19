package com.dprieto.marvelapp.data.local

import com.dprieto.marvelapp.data.local.models.MarvelHeroLocal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(private val dao: MarvelDao): LocalDataSource {


    override fun getCharacters(): Flow<List<MarvelHeroLocal>> {
        return dao.getCharacters()
    }

    override fun insertCharacters(localCharacterList: List<MarvelHeroLocal>) {
        dao.insertCharacters(localCharacterList)
    }

    override fun updateCharacter(hero: MarvelHeroLocal) {
        dao.updateCharacter(hero)
    }

    override fun existCharacters(): Boolean {
        return dao.countCharacters()  != 0
    }


}