package com.dprieto.marvelapp.data.mappers

import com.dprieto.marvelapp.data.local.models.MarvelHeroLocal
import com.dprieto.marvelapp.data.remote.models.MarvelResponse
import javax.inject.Inject

class RemoteToLocalMapper @Inject constructor() {

    fun map(marvelResponse: MarvelResponse): List<MarvelHeroLocal> {
        return marvelResponse.data.results.map {
            MarvelHeroLocal(it.id.toString(), it.name ?: "", it.thumbnail.getImageUrlLandscape()
            )
        }
    }
}