package com.tgyuu.feature.profile.setting

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
import com.tgyuu.feature.profile.R

@Composable
internal fun SettingRoute() {
    val snackbarHostState = remember { SnackbarHostState() }

    SettingScreen(snackbarHostState = snackbarHostState)
}

@Composable
fun SettingScreen(snackbarHostState: SnackbarHostState) {
    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            BaekyoungCenterTopBar(
                titleTextId = R.string.my_account,
                showBackButton = true,
            )
        }
    }
}
