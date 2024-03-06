package com.tgyuu.baekyoung_i.auth.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun BaekyoungCloud(
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
            }
    ) {
        drawOval(
            color = Color.White,
            size = Size(size.width * 2 / 3, size.height / 11),
            alpha = 0.85F,
        )
    }
}

@Preview
@Composable
internal fun BaekyoungCloudPreview() {
    BaekyoungTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BaekyoungTheme.colors.blueF5FF)
        ) {
            var isButtonClicked by remember { mutableStateOf(true) }
            val animateOffset by animateDpAsState(
                targetValue = if (isButtonClicked) 0.dp else -666.dp,
                animationSpec = spring(stiffness = 5f)
            )

            Text(
                "임시 스위치",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 50.dp)
                    .clickable { isButtonClicked = !isButtonClicked }
            )

            BaekyoungCloud(
                offsetX = -110.dp,
                offsetY = 90.dp,
                translationY = animateOffset,
            )

            BaekyoungCloud(
                offsetX = 251.dp,
                offsetY = 226.dp,
                translationY = animateOffset,
            )

            BaekyoungCloud(
                offsetX = -60.dp,
                offsetY = 515.dp,
                translationY = animateOffset,
            )

            BaekyoungCloud(
                offsetX = 60.dp,
                offsetY = 755.dp,
                translationY = animateOffset,
            )

            BaekyoungCloud(
                offsetX = -110.dp,
                offsetY = 889.dp,
                translationY = animateOffset,
            )

            BaekyoungCloud(
                offsetX = 219.dp,
                offsetY = 936.dp,
                translationY = animateOffset,
            )
        }
    }
}