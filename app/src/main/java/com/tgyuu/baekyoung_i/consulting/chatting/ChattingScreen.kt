package com.tgyuu.baekyoung_i.consulting.chatting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pknu.model.consulting.ConsultingChatting
import com.tgyuu.baekyoung_i.R
import com.tgyuu.common.util.UiState
import com.tgyuu.designsystem.component.BaekyoungCenterTopBar
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun ChattingRoute(viewModel: ChattingViewModel = hiltViewModel()) {
    val chat by viewModel.chat.collectAsStateWithLifecycle()
    val userInput by viewModel.userInput.collectAsStateWithLifecycle()

    ChattingScreen(
        chat,
        userInput,
        viewModel::setUserInput,
        viewModel::postUserChatting,
    )
}

@Composable
internal fun ChattingScreen(
    chat: UiState<List<ConsultingChatting>>,
    userInput: String,
    onUserInputChanged: (String) -> Unit,
    postUserChatting: () -> Unit,
) {
    val backgroundColor = Brush.verticalGradient(
        listOf(
            BaekyoungTheme.colors.blue4E,
            BaekyoungTheme.colors.blue4E.copy(alpha = 0.66f),
        )
    )

    Scaffold(
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(backgroundColor)
        ) {
            BaekyoungCenterTopBar(
                titleTextId = R.string.chatting,
                textColor = BaekyoungTheme.colors.white,
                showSearchButton = true,
            )
        }
    }
}