package com.tgyuu.baekyoung_i.consulting.chatting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pknu.model.consulting.ConsultingChatting
import com.tgyuu.baekyoung_i.main.navigation.TopLevelDestination
import com.tgyuu.common.util.UiState
import com.tgyuu.designsystem.component.BaekyoungTopAppBar
import com.tgyuu.designsystem.component.Loader
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun ChattingRoute(viewModel: ChattingViewModel = hiltViewModel()) {
    val chat by viewModel.chat.collectAsStateWithLifecycle()
    val userInput by viewModel.userInput.collectAsStateWithLifecycle()

    ChattingScreen(
        chat,
        userInput,
        viewModel::setUserInput
    )
}

@Composable
internal fun ChattingScreen(
    chat: UiState,
    userInput: String,
    onUserInputChanged: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BaekyoungTheme.colors.blueEFF)
    ) {

        if (chat is UiState.Loading) {
            Loader()
        }

        Column(modifier = Modifier.fillMaxSize()) {
            BaekyoungTopAppBar(TopLevelDestination.CONSULTING.titleTextId)

            if (chat is UiState.Success<ConsultingChatting>) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.Black)
                ) {

                }

                BasicTextField(
                    value = userInput,
                    onValueChange = onUserInputChanged,
                    textStyle = BaekyoungTheme.typography.contentNormal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {

                }
            }
        }
    }
}