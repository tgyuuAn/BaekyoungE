package com.tgyuu.feature.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tgyuu.common.util.addFocusCleaner
import com.tgyuu.designsystem.R.drawable
import com.tgyuu.designsystem.R.string
import com.tgyuu.designsystem.component.BaekgyoungClouds
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun HomeRoute(viewModel: HomeViewModel = hiltViewModel()) {
    HomeScreen()
}

@Composable
internal fun HomeScreen() {
    val focusManager = LocalFocusManager.current
    val localConfiguration = LocalConfiguration.current
    var showTopBar by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        showTopBar = true
    }

    val backgroundColor = Brush.verticalGradient(
        listOf(
            BaekyoungTheme.colors.blueEDFF,
            BaekyoungTheme.colors.white,
        ),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .addFocusCleaner(focusManager = focusManager),
    ) {
        BaekgyoungClouds(animateOffset = -ANIMATION_OFFSET.dp)

        Image(
            painter = painterResource(id = drawable.ic_sea),
            contentDescription = stringResource(id = string.sea_description),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .offset(y = localConfiguration.screenHeightDp.dp + -SEA_IMAGE_HEIGHT.dp),
        )

        Image(
            painter = painterResource(id = drawable.ic_main_baekgyoung),
            contentDescription = stringResource(id = string.main_baekgyoung_description),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .offset(y = localConfiguration.screenHeightDp.dp + -SEA_IMAGE_HEIGHT.dp - 43.dp),
        )

        AnimatedVisibility(
            visible = showTopBar,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(BaekyoungTheme.colors.white.copy(alpha = 0.4f)),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(10.dp),
                ) {
                    Image(
                        painterResource(id = R.drawable.ic_fish),
                        contentDescription = stringResource(id = R.string.fish_description),
                    )

                    Text(
                        text = "50",
                        style = BaekyoungTheme.typography.contentBold,
                        color = BaekyoungTheme.colors.white,
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(10.dp),
                ) {
                    Image(
                        painterResource(id = R.drawable.ic_chat),
                        contentDescription = stringResource(id = R.string.chat_description),
                    )

                    Image(
                        painterResource(id = R.drawable.ic_setting),
                        contentDescription = stringResource(id = R.string.setting_description),
                    )
                }
            }
        }
    }
}

private val SEA_IMAGE_HEIGHT = 166
private val ANIMATION_OFFSET = 1360
