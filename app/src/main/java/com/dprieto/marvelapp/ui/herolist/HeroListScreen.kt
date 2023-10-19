@file:OptIn(ExperimentalMaterialApi::class)

package com.dprieto.marvelapp.ui.herolist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.dprieto.marvelapp.domain.MarvelCharacter
import com.dprieto.marvelapp.ui.components.AppBackground
import com.dprieto.marvelapp.ui.theme.MarvelColor


@Preview(showBackground = true)
@Composable
fun HeroListScreen(
    viewModel: HeroListViewModel = hiltViewModel(),
    onHeroClick: (MarvelCharacter) -> Unit = { _ -> }
) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "Personajes",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            backgroundColor = MarvelColor,
            elevation = 4.dp
        )
    }
    ) {

        val characters = viewModel.characters.collectAsState()

        AppBackground()
        HeroList(characters.value, onHeroClick = { onHeroClick(it) }, onFavoriteClick = {
            viewModel.setFavorite(it)
        })

    }

}


@Composable
fun HeroList(
    characters: List<MarvelCharacter>, onHeroClick: (MarvelCharacter) -> Unit = { _ -> },
    onFavoriteClick: (MarvelCharacter) -> Unit = { _ -> }
) {
    LazyColumn(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            items(characters) { character ->
                HeroListItem(character, onHeroClick = {
                    onHeroClick(it)
                }, onFavoriteClick = {
                    onFavoriteClick(
                        MarvelCharacter(
                            character.id,
                            character.name,
                            character.photo,
                            isFavorite = it
                        )
                    )
                })
            }
        })
}

@Preview(showBackground = true)
@Composable
fun HeroListItem(
    character: MarvelCharacter = MarvelCharacter("", "", ""),
    onHeroClick: (MarvelCharacter) -> Unit = { _ -> },
    onFavoriteClick: (Boolean) -> Unit = { _ -> }
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(15.dp)
            .testTag("ListItem"),
        shape = RoundedCornerShape(30.dp),
        onClick = {
            onHeroClick(character)
        }
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = character.photo),
            contentDescription = "Hero item image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = character.name,
                fontSize = 26.sp,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(15.dp)
            )

            FavIcon(character.isFavorite) {
                onFavoriteClick(it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavIcon(isFavourite: Boolean = false, onFavoriteClick: (Boolean) -> Unit = { _ -> }) {
    Icon(
        painter = painterResource(
            id =
            android.R.drawable.star_big_off

        ),
        contentDescription = "Fav icon",
        modifier = Modifier
            .padding(10.dp)
            .width(40.dp)
            .height(40.dp)
            .clickable { onFavoriteClick(!isFavourite) },
        tint = if (isFavourite) {
            Color.Yellow
        } else {
            Color.DarkGray
        }


    )


}