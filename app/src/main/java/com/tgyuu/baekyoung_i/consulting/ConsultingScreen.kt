package com.tgyuu.baekyoung_i.consulting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tgyuu.baekyoung_i.main.navigation.TopLevelDestination.CONSULTING
import com.tgyuu.designsystem.component.BaekyoungTopAppBar
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
fun ConsultingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BaekyoungTheme.colors.blue5FF)
    ) {
        BaekyoungTopAppBar(CONSULTING.titleTextId)
    }
}