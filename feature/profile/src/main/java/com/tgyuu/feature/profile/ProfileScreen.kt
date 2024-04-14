package com.tgyuu.feature.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun ProfileRoute() {
    ProfileScreen()
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BaekyoungTheme.colors.white),
    ) {}
}
