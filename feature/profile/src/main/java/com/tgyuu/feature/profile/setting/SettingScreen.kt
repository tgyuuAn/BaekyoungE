package com.tgyuu.feature.profile.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tgyuu.designsystem.component.BaekyoungCenterTopBar
import com.tgyuu.designsystem.theme.BaekyoungTheme
import com.tgyuu.feature.profile.R

@Composable
internal fun SettingRoute(popBackStack: () -> Unit) {
    val snackbarHostState = remember { SnackbarHostState() }

    SettingScreen(
        snackbarHostState = snackbarHostState,
        popBackStack = popBackStack,
    )
}

@Composable
fun SettingScreen(
    snackbarHostState: SnackbarHostState,
    popBackStack: () -> Unit,
) {
    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(BaekyoungTheme.colors.white),
        ) {
            BaekyoungCenterTopBar(
                titleTextId = R.string.my_account,
                showBackButton = true,
                onClickBackButton = popBackStack,
            )

            Box(modifier = Modifier.padding(top = 25.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_user_default),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.TopCenter),
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 3.dp, top = 32.dp),
                )
            }
        }
    }
}
