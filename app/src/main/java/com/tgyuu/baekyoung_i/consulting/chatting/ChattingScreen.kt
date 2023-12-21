package com.tgyuu.baekyoung_i.consulting.chatting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.tgyuu.baekyoung_i.main.navigation.TopLevelDestination
import com.tgyuu.designsystem.component.BaekyoungTopAppBar

@Composable
internal fun ChattingRoute(viewModel: ChattingViewModel = hiltViewModel()) {
    ChattingScreen()
}

@Composable
internal fun ChattingScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        BaekyoungTopAppBar(TopLevelDestination.CONSULTING.titleTextId)

    }
}