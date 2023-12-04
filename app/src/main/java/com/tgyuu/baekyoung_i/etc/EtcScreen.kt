package com.tgyuu.baekyoung_i.etc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tgyuu.baekyoung_i.etc.component.SettingBar
import com.tgyuu.baekyoung_i.main.navigation.TopLevelDestination.ETC
import com.tgyuu.designsystem.component.BaekyoungTopAppBar
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
fun EtcScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BaekyoungTheme.colors.blue5FF)
    ) {
        BaekyoungTopAppBar(ETC.titleTextId)
    }
}