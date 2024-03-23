package com.tgyuu.baekyounge.splash

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tgyuu.baekyounge.R

@Composable
internal fun SplashRoute() {
    SplashScreen()
}

@Composable
internal fun SplashScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.ic_splash_background),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize(),
        )

        SplashBackground()
    }
}

@Composable
private fun SplashBackground() {
    val localConfiguration = LocalConfiguration.current

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .blur(100.dp)
            .offset(
                x = localConfiguration.screenWidthDp.dp * 5 / 8,
                y = -localConfiguration.screenHeightDp.dp * 9 / 20,
            ),
    ) {
        drawCircle(
            color = Color(0xFF6CEDFF),
            radius = size.width * 2 / 3,
            alpha = 0.4F,
        )
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .blur(100.dp)
            .offset(
                x = -localConfiguration.screenWidthDp.dp * 3 / 7,
                y = localConfiguration.screenHeightDp.dp * 1 / 9,
            ),
    ) {
        drawCircle(
            color = Color(0xFF6CEDFF),
            radius = size.width * 1 / 5,
            alpha = 0.4F,
        )
    }
}
