package com.dprieto.marvelapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarvelCharacter(
    val id:String,
    val name: String,
    val photo: String,
    var isFavorite: Boolean = false
): Parcelable