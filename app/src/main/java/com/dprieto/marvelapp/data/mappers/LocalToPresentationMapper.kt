package com.dprieto.marvelapp.data.mappers

import com.dprieto.marvelapp.data.local.models.MarvelHeroLocal
import com.dprieto.marvelapp.domain.MarvelCharacter
import javax.inject.Inject

class LocalToPresentationMapper @Inject constructor(){

    fun map(characterList: List<MarvelHeroLocal>): List<MarvelCharacter> {
        return characterList.map { map(it) }
    }

    fun map(character:MarvelHeroLocal): MarvelCharacter {
        return MarvelCharacter(character.id,character.name,character.photo, character.favorite)
    }

}