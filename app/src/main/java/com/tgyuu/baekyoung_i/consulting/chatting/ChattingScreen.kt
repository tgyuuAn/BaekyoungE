package com.tgyuu.baekyoung_i.consulting.chatting

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pknu.model.consulting.ConsultingChatting
import com.tgyuu.baekyoung_i.R
import com.tgyuu.common.util.UiState
import com.tgyuu.designsystem.component.BaekyoungCenterTopBar
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun ChattingRoute(
    viewModel: ChattingViewModel = hiltViewModel(),
) {
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
    val localConfiguration = LocalConfiguration.current
    val backgroundColor = Brush.verticalGradient(
        listOf(
            BaekyoungTheme.colors.blue4E,
            BaekyoungTheme.colors.blue4E.copy(alpha = 0.66f),
        )
    )

    Scaffold(contentWindowInsets = WindowInsets(0.dp)) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(backgroundColor)
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(100.dp)
                    .offset(
                        x = localConfiguration.screenWidthDp.dp * 5 / 8,
                        y = -localConfiguration.screenHeightDp.dp * 9 / 20,
                    )
            ) {
                drawCircle(
                    color = Color(0xFF6CEDFF),
                    radius = size.width*2/3,
                    alpha = 0.4F,
                )
            }

            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(100.dp)
                    .offset(
                        x = -localConfiguration.screenWidthDp.dp * 3 / 7,
                        y = localConfiguration.screenHeightDp.dp * 1 / 9,
                    )
            ) {
                drawCircle(
                    color = Color(0xFF6CEDFF),
                    radius = size.width*1/5,
                    alpha = 0.4F,
                )
            }

            BaekyoungCenterTopBar(
                titleTextId = R.string.consulting,
                textColor = BaekyoungTheme.colors.white,
                showSearchButton = true,
            )

            Image(
                painter = painterResource(id = R.drawable.ic_ai_chat_background),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}