package com.dprieto.marvelapp

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import com.dprieto.marvelapp.domain.MarvelCharacter
import com.dprieto.marvelapp.ui.herolist.HeroList
import org.junit.Rule
import org.junit.Test

class HeroListScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun addItemsToList(){

        val characters = listOf(
            MarvelCharacter("1", "Spider-Man", "image_url_1"),
            MarvelCharacter("2", "Iron Man", "image_url_2"),
            MarvelCharacter("3", "Captain America", "image_url_3"),
        )

        composeRule.setContent {
            HeroList(characters)
        }

        Thread.sleep(3000)

        composeRule.onAllNodesWithTag("ListItem").assertCountEquals(3)


    }

}