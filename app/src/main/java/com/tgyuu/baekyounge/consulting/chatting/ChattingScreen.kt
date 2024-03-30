package com.tgyuu.baekyounge.consulting.chatting

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tgyuu.model.consulting.ConsultingChatting
import com.tgyuu.baekyounge.R
import com.tgyuu.common.util.UiState
import com.tgyuu.common.util.addFocusCleaner
import com.tgyuu.designsystem.component.BaekyoungCenterTopBar
import com.tgyuu.designsystem.component.BaekyoungChatTextField
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun ChattingRoute(
    popBackStack: () -> Unit,
    viewModel: ChattingViewModel = hiltViewModel(),
) {
    val chat by viewModel.chat.collectAsStateWithLifecycle()
    val chatText by viewModel.chatText.collectAsStateWithLifecycle()
    val searchText by viewModel.searchText.collectAsStateWithLifecycle()

    ChattingScreen(
        chat = chat,
        chatText = chatText,
        searchText = searchText,
        onChatTextChanged = viewModel::setChatText,
        onSearchTextChanged = viewModel::setSearchText,
        postUserChatting = viewModel::postUserChatting,
        popBackStack = popBackStack,
    )
}

@Composable
internal fun ChattingScreen(
    chat: UiState<List<ConsultingChatting>>,
    chatText: String,
    searchText: String,
    onChatTextChanged: (String) -> Unit,
    onSearchTextChanged: (String) -> Unit,
    postUserChatting: () -> Unit,
    popBackStack: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val backgroundColor = Brush.verticalGradient(
        listOf(
            BaekyoungTheme.colors.blue4E,
            BaekyoungTheme.colors.blue4E.copy(alpha = 0.66f),
        ),
    )

    Scaffold(contentWindowInsets = WindowInsets(0.dp)) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(backgroundColor)
                .addFocusCleaner(focusManager),
        ) {
            ConsultingChattingBackground()

            BaekyoungCenterTopBar(
                titleTextId = R.string.consulting,
                textColor = BaekyoungTheme.colors.white,
                showSearchButton = true,
                searchText = searchText,
                onSearchTextChanged = onSearchTextChanged,
                clearSearchText = { onSearchTextChanged("") },
                onClickBackButton = popBackStack,
            )

            Image(
                painter = painterResource(id = R.drawable.ic_ai_chat_background),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
            )

            Image(
                painter = painterResource(id = R.drawable.ic_consulting_baekgyoung),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(start = 20.dp, bottom = 80.dp),
            )

            BaekyoungChatTextField(
                chatText = chatText,
                onTextChanged = onChatTextChanged,
                sendMessage = postUserChatting,
                textColor = BaekyoungTheme.colors.white,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
            )
        }
    }
}

@Composable
private fun ConsultingChattingBackground() {
    val localConfiguration = LocalConfiguration.current

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .blur(100.dp)
            .offset(
                x = localConfiguration.screenWidthDp.dp * 5 / 8,
                y = -localConfiguration.screenHeightDp.dp * 9 / 20,
            ),
    ) {
        drawCircle(
            color = Color(0xFF6CEDFF),
            radius = size.width * 2 / 3,
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
            ),
    ) {
        drawCircle(
            color = Color(0xFF6CEDFF),
            radius = size.width * 1 / 5,
            alpha = 0.4F,
        )
    }
}
