package com.dprieto.marvelapp.data.remote.models

data class MarvelResponse (
    val code: Long,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: DataMarvel
)

data class DataMarvel (
    val offset: Long,
    val limit: Long,
    val total: Long,
    val count: Long,
    val results: List<Result>
)

data class Result (
    val id: Int,
    val name: String?,
    val title: String?,
    val description: String?,
    val thumbnail: Thumbnail,
    val resourceURI: String?
)

data class Thumbnail (
    val path: String,
    val extension: String
){
    fun getImageUrlLandscape(): String {
        return "$path/${"landscape_xlarge"}.${extension}"
    }
    fun getImageUrlPortrait(): String {
        return "$path/${"portrait_xlarge"}.${extension}"
    }
}
