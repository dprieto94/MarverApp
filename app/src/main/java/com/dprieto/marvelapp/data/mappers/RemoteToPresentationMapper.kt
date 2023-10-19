package com.dprieto.marvelapp.data.mappers

import com.dprieto.marvelapp.data.remote.models.MarvelResponse
import com.dprieto.marvelapp.domain.MarvelMedia
import javax.inject.Inject

class RemoteToPresentationMapper @Inject constructor() {

    fun map(marvelResponse: MarvelResponse):List<MarvelMedia> {
        return marvelResponse.data.results.map {
            MarvelMedia(
                it.id.toString(),
                it.title ?: "",
                it.thumbnail.getImageUrlPortrait()
            )
        }
    }

}