package com.tgyuu.feature.etc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun EtcRoute() {
    EtcScreen()
}

@Composable
fun EtcScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BaekyoungTheme.colors.white),
    ) {}
}
