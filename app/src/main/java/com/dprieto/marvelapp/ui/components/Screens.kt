package com.dprieto.marvelapp.ui.components

import android.os.Bundle

sealed class Screens(val route: String){
    companion object{
        private const val SPLASH_BASE_ROUTE = "splash"
        private const val HERO_LIST_BASE_ROUTE = "home"
        private const val HERO_DETAIL_BASE_ROUTE = "detail"
        private const val HERO_DETAIL_ROUTE = "detail/{marvelcharacter}"
    }

    object Splash: Screens(SPLASH_BASE_ROUTE)
    object HeroList: Screens(HERO_LIST_BASE_ROUTE)
    object HeroDetail: Screens(HERO_DETAIL_BASE_ROUTE){
        const val ARG_ID = "id"
        const val ARG_CHARACTER = "marvelcharacter"

        fun generateRoute(bundleCharacter: Bundle): String{
            return "$HERO_DETAIL_BASE_ROUTE/${bundleCharacter}"
        }
    }
}
