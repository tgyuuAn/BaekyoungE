package com.tgyuu.baekyounge.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tgyuu.baekyounge.R
import com.tgyuu.designsystem.theme.BaekyoungTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun SplashRoute(
    navigateToAuth: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    val canSkipSplash by viewModel.canSkipSplash.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.eventFlow.collectLatest {
            when (it) {
                is SplashViewModel.SplashEvent.NavigateToAuth -> navigateToAuth()
            }
        }
    }

    SplashScreen(
        canSkipSplash = canSkipSplash,
        onClick = { viewModel.event(SplashViewModel.SplashEvent.NavigateToAuth) },
    )
}

@Composable
internal fun SplashScreen(
    canSkipSplash: Boolean,
    onClick: () -> Unit,
) {
    val localConfiguration = LocalConfiguration.current
    val localDensity = LocalDensity.current
    var showAnimation by remember { mutableStateOf(false) }
    val animateOffset by animateOffsetAsState(
        targetValue = if (!showAnimation) {
            Offset(0f, 0f)
        } else {
            Offset(
                with(localDensity) { -localConfiguration.screenWidthDp.dp.toPx() },
                with(localDensity) { localConfiguration.screenHeightDp.dp.toPx() * 2 / 5 },
            )
        },
        animationSpec = tween(SHOOTING_STAR_ANIMATION_DURAION),
    )

    LaunchedEffect(true) {
        showAnimation = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                if (canSkipSplash) {
                    onClick()
                }
            },
    ) {
        Image(
            painter = painterResource(R.drawable.ic_splash_background),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize(),
        )

        SplashBackground()

        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 50.dp),
        ) {
            AnimatedVisibility(
                visible = showAnimation,
                enter = fadeIn(tween(1000, SHOOTING_STAR_ANIMATION_DURAION + 1000)),
                exit = fadeOut(),
            ) {
                Text(
                    text = "백",
                    style = BaekyoungTheme.typography.splashBold.copy(fontSize = 30.sp),
                    color = BaekyoungTheme.colors.white,
                )
            }

            AnimatedVisibility(
                visible = showAnimation,
                enter = fadeIn(tween(1000, SHOOTING_STAR_ANIMATION_DURAION + 2000)),
                exit = fadeOut(),
            ) {
                Text(
                    text = "경",
                    style = BaekyoungTheme.typography.splashBold.copy(fontSize = 30.sp),
                    color = BaekyoungTheme.colors.white,
                )
            }

            AnimatedVisibility(
                visible = showAnimation,
                enter = fadeIn(tween(1000, SHOOTING_STAR_ANIMATION_DURAION + 3000)),
                exit = fadeOut(),
            ) {
                Text(
                    text = "이",
                    style = BaekyoungTheme.typography.splashBold.copy(fontSize = 30.sp),
                    color = BaekyoungTheme.colors.white,
                )
            }
        }

        AnimatedVisibility(
            visible = showAnimation,
            enter = fadeIn(tween(1000, SHOOTING_STAR_ANIMATION_DURAION)),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp),
        ) {
            Text(
                text = stringResource(id = R.string.press_any_key_to_start),
                style = BaekyoungTheme.typography.splashBold,
                color = BaekyoungTheme.colors.white.copy(alpha = 0.8f),
            )
        }

        ShootingStar(animatedOffset = animateOffset)
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

@Composable
private fun ShootingStar(
    animatedOffset: Offset,
    modifier: Modifier = Modifier,
) {
    val localConfiguration = LocalConfiguration.current

    Image(
        painter = painterResource(id = R.drawable.ic_shooting_star),
        contentDescription = stringResource(id = R.string.shooting_star_description),
        modifier = modifier
            .offset((localConfiguration.screenWidthDp.dp - 78.dp), -47.dp)
            .graphicsLayer {
                this.translationX = animatedOffset.x
                this.translationY = animatedOffset.y
            },
    )
}

val SHOOTING_STAR_ANIMATION_DURAION = 3000
