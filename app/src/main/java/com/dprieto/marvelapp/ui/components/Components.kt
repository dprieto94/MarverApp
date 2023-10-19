package com.dprieto.marvelapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dprieto.marvelapp.R
import com.dprieto.marvelapp.ui.theme.MarvelColor

@Preview(showBackground = true)
@Composable
fun AppBackground(){
    Image(
        painter = painterResource(id = R.drawable.space_image),
        contentDescription = "Space background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop

    )
}

@Preview(showBackground = true)
@Composable
fun RoundedLoader(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(verticalArrangement = Arrangement.Center) {
            CircularProgressIndicator(color = MarvelColor, modifier = Modifier.size(48.dp))
        }
    }

}