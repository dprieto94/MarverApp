package com.dprieto.marvelapp.ui.herodetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.dprieto.marvelapp.domain.MarvelCharacter
import com.dprieto.marvelapp.domain.MarvelMedia
import com.dprieto.marvelapp.ui.components.AppBackground
import com.dprieto.marvelapp.ui.components.RoundedLoader
import com.dprieto.marvelapp.ui.theme.MarvelColor

@Preview(showBackground = true)
@Composable
fun HeroDetailScreen(
    character: MarvelCharacter = MarvelCharacter("1", "", ""),
    viewModel: HeroDetailViewModel = hiltViewModel(),
    onBackPressed: () -> Unit = {}
) {

    val MEDIA_TYPE_SERIES = "Series"
    val MEDIA_TYPE_COMICS = "Comics"
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getMedia(character.id.toInt())
    })



    Scaffold(modifier = Modifier.fillMaxSize().scrollable(scrollState, orientation = Orientation.Vertical), topBar = {
        TopAppBar(
            title = {
                Text(
                    text = character.name,
                    color = Color.White
                )
            },
            navigationIcon = {
                IconButton(onClick = { onBackPressed() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Atras", tint = Color.White)
                }
            },
            backgroundColor = MarvelColor,
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            elevation = 4.dp
        )
    }) {
        val series = viewModel.series.collectAsState()
        val comics = viewModel.comics.collectAsState()

        AppBackground()

        Column(Modifier.fillMaxSize()) {
            HeroDataComponent(character.name, character.photo)

            if (series.value.isEmpty() && comics.value.isEmpty() ){
                RoundedLoader()
            }else{
                MediaList(MEDIA_TYPE_SERIES, series.value)
                MediaList(MEDIA_TYPE_COMICS, comics.value)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HeroDataComponent(
    name: String = "",
    photo: String = ""
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = photo),
            contentDescription = "Hero Image Detail",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
        Text(
            text = name, fontSize = 26.sp,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold
        )

    }
}

@Preview(showBackground = true)
@Composable
fun MediaList(mediaType: String = "", medias: List<MarvelMedia> = emptyList()) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Text(
            text = mediaType, modifier = Modifier
                .fillMaxWidth()
                .background(MarvelColor).padding(start = 7.dp), fontWeight = FontWeight.Bold, color = Color.White
        )
        LazyRow(modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            content = {
                items(medias) { media ->
                    MediaListItem(media)
                }
            })
    }

}

@Preview(showBackground = true)
@Composable
fun MediaListItem(media: MarvelMedia = MarvelMedia("", "", "")) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(150.dp).padding(start = 7.dp, end = 7.dp), contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = media.photo),
            contentDescription = "Hero Media Item",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Text(
            text = media.title,
            color = Color.White,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.background(Color.DarkGray).padding(start = 5.dp, end = 5.dp)
        )
    }
}


