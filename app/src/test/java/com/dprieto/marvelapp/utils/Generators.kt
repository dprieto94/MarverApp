package com.dprieto.marvelapp.utils

import com.dprieto.marvelapp.data.local.models.MarvelHeroLocal
import com.dprieto.marvelapp.data.remote.models.DataMarvel
import com.dprieto.marvelapp.data.remote.models.MarvelResponse
import com.dprieto.marvelapp.data.remote.models.Result
import com.dprieto.marvelapp.data.remote.models.Thumbnail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


fun generateMarvelResponseModel(): MarvelResponse {
    return MarvelResponse(
        200,
        "",
        "",
        "",
        "",
        "",
        DataMarvel(
            0,
            0,
            100,
            10,
            (0 until 5).map {
                Result(
                    it,
                    "Name: $it",
                    "Title: $it",
                    "Description: $it",
                    Thumbnail("Path: $it", "Extension: $it"),
                    "Url: $it"
                )

            }
        )

    )
}

fun generateHerosLocal(): Flow<List<MarvelHeroLocal>> {
    return flow {
        emit((0 until 5).map {
            MarvelHeroLocal("ID: $it", "Name: $it", "Photo: $it", it % 2 == 0)
        })
    }
}

fun generateCharacters(): MarvelResponse {
    return generateMarvelResponseModel()
}

fun generateSeries(): Flow<MarvelResponse> {
    return flow {
        emit(generateMarvelResponseModel())
    }
}

fun generateComics(): Flow<MarvelResponse> {
    return flow {
        emit(generateMarvelResponseModel())
    }
}