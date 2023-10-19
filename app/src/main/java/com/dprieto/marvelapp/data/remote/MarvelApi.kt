package com.dprieto.marvelapp.data.remote

import com.dprieto.marvelapp.data.remote.models.MarvelResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelApi {

    @GET("v1/public/characters")
    suspend fun getCharacters(): MarvelResponse

    @GET("v1/public/characters/{characterId}/comics")
    suspend fun getComics(@Path("characterId") characterId: Int): MarvelResponse

    @GET("v1/public/characters/{characterId}/series")
    suspend fun getSeries(@Path("characterId") characterId: Int): MarvelResponse



}