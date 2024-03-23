package com.tgyuu.designsystem.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BaekyoungCloud(
    offsetX: Dp,
    offsetY: Dp,
    translationY: Dp,
    modifier: Modifier = Modifier,
) {
    Canvas(
        modifier = modifier
            .fillMaxSize()
            .blur(40.dp)
            .offset(offsetX, offsetY)
            .graphicsLayer {
                this.translationY = translationY.toPx()
            },
    ) {
        drawOval(
            color = Color.White,
            size = Size(size.width * 2 / 3, size.height / 11),
            alpha = 0.85F,
        )
    }
}

@Composable
fun BaekgyoungClouds(animateOffset: Dp) {
    Box(modifier = Modifier.fillMaxSize()) {
        BaekyoungCloud(
            offsetX = -110.dp,
            offsetY = 40.dp,
            translationY = animateOffset,
        )

        BaekyoungCloud(
            offsetX = 251.dp,
            offsetY = 270.dp,
            translationY = animateOffset,
        )

        BaekyoungCloud(
            offsetX = -60.dp,
            offsetY = 420.dp,
            translationY = animateOffset,
        )

        BaekyoungCloud(
            offsetX = 60.dp,
            offsetY = 610.dp,
            translationY = animateOffset,
        )

        BaekyoungCloud(
            offsetX = -110.dp,
            offsetY = 936.dp,
            translationY = animateOffset,
        )

        BaekyoungCloud(
            offsetX = 251.dp,
            offsetY = 1130.dp,
            translationY = animateOffset,
        )

        BaekyoungCloud(
            offsetX = -60.dp,
            offsetY = 1280.dp,
            translationY = animateOffset,
        )

        BaekyoungCloud(
            offsetX = 60.dp,
            offsetY = 1470.dp,
            translationY = animateOffset,
        )

        BaekyoungCloud(
            offsetX = -110.dp,
            offsetY = 1736.dp,
            translationY = animateOffset,
        )
    }
}
