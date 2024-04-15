package com.tgyuu.feature.profile.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
        }
    }
}
