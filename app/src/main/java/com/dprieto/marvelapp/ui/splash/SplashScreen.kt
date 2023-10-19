package com.dprieto.marvelapp.ui.splash


import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dprieto.marvelapp.R
import com.dprieto.marvelapp.ui.components.AppBackground
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun SplashScreen(onSplashEnds: () -> Unit = { }) {

    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(1000L)
        onSplashEnds()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {

        AppBackground()

        Image(
            painter = painterResource(id = R.drawable.marvel_logo),
            contentDescription = "Marvel logo splash",
            modifier = Modifier
                .padding(30.dp)
                .align(Alignment.CenterStart)

        )
    }
}
