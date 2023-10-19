package com.dprieto.marvelapp.data.mappers

import com.dprieto.marvelapp.data.local.models.MarvelHeroLocal
import com.dprieto.marvelapp.domain.MarvelCharacter
import javax.inject.Inject

class PresentationToLocalMapper @Inject constructor(){

    fun map(character: MarvelCharacter): MarvelHeroLocal {
        return MarvelHeroLocal(character.id, character.name, character.photo, character.isFavorite)
    }

}