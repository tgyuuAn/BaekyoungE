package com.tgyuu.baekyoung_i.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tgyuu.baekyoung_i.R
import com.tgyuu.common.util.addFocusCleaner
import com.tgyuu.designsystem.component.BaekgyoungClouds
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun HomeRoute() {
    HomeScreen()
}

@Composable
internal fun HomeScreen() {
    val focusManager = LocalFocusManager.current
    val localConfiguration = LocalConfiguration.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BaekyoungTheme.colors.blueF5FF)
            .addFocusCleaner(focusManager = focusManager)
    ) {
        BaekgyoungClouds(animateOffset = -ANIMATION_OFFSET.dp)

        Image(
            painter = painterResource(id = R.drawable.ic_sea),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .offset(y = localConfiguration.screenHeightDp.dp + -SEA_IMAGE_HEIGHT.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_main_baekgyoung),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .offset(y = localConfiguration.screenHeightDp.dp + -SEA_IMAGE_HEIGHT.dp - 43.dp)
        )

        Box(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(BaekyoungTheme.colors.white.copy(alpha = 0.3f)),
        ) {
        }
    }
}

private val SEA_IMAGE_HEIGHT = 166
private val ANIMATION_OFFSET = 1360