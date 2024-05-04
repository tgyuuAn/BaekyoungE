package com.tgyuu.feature.mentoring_mentee

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tgyuu.designsystem.component.BaekyoungCenterTopBar
import com.tgyuu.designsystem.theme.BaekyoungTheme
import com.tgyuu.feature.`mentoring-mentee`.R

@Composable
internal fun MentoringMenteeRoute(popBackStack: () -> Unit) {
    MentoringMenteeScreen(popBackStack = popBackStack)
}

@Composable
fun MentoringMenteeScreen(popBackStack: () -> Unit) {
    Scaffold(
        containerColor = BaekyoungTheme.colors.grayF5,
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(BaekyoungTheme.colors.white),
        ) {
            BaekyoungCenterTopBar(
                titleTextId = R.string.chatting_room,
                showBackButton = true,
                onClickBackButton = popBackStack,
            )
        }
    }
}
