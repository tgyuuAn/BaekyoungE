package com.tgyuu.baekyoung_i.community

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tgyuu.baekyoung_i.main.navigation.TopLevelDestination.COMMUNITY
import com.tgyuu.designsystem.component.BaekyoungTopBar
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun CommunityRoute() {
    CommunityScreen()
}

@Composable
fun CommunityScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BaekyoungTheme.colors.blue5FF)
    ) {
        BaekyoungTopBar(COMMUNITY.titleTextId)
    }
}