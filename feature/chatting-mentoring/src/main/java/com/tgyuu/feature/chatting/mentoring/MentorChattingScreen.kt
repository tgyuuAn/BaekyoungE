package com.tgyuu.feature.chatting.mentoring

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tgyuu.common.util.UiState
import com.tgyuu.common.util.addFocusCleaner
import com.tgyuu.designsystem.R.string
import com.tgyuu.designsystem.component.BaekyoungButton
import com.tgyuu.designsystem.component.BaekyoungCenterTopBar
import com.tgyuu.designsystem.component.BaekyoungChatTextField
import com.tgyuu.designsystem.component.BaekyoungRow
import com.tgyuu.designsystem.component.BaekyoungSpeechBubble
import com.tgyuu.designsystem.component.ChattingLoader
import com.tgyuu.designsystem.component.SpeechBubbleType
import com.tgyuu.designsystem.theme.BaekyoungTheme
import com.tgyuu.model.consulting.ChattingRole
import com.tgyuu.model.consulting.Message
import kotlinx.coroutines.launch

@Composable
internal fun MentorChattingRoute(
    roomId: String,
    popBackStack: () -> Unit,
    viewModel: MentorChattingViewModel = hiltViewModel(),
) {
    val chatText by viewModel.chatText.collectAsStateWithLifecycle()
    val chatLog = viewModel.chatLog.toList()
    val searchText by viewModel.searchText.collectAsStateWithLifecycle()
    val chatState by viewModel.chatState.collectAsStateWithLifecycle()
    val (showExitChattingRoomDialog, setExitChattingRoomDialog) = remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        Log.d("test", "roomId : $roomId")
        viewModel.roomId.value = roomId
    }

    MentorChattingScreen(
        chatText = chatText,
        searchText = searchText,
        chatLog = chatLog,
        chatState = chatState,
        showExitChattingRoomDialog = showExitChattingRoomDialog,
        setExitChattingRoomDialog = setExitChattingRoomDialog,
        onChatTextChanged = viewModel::setChatText,
        onSearchTextChanged = viewModel::setSearchText,
        popBackStack = popBackStack,
    )
}

@Composable
internal fun MentorChattingScreen(
    chatText: String,
    searchText: String,
    chatLog: List<Message>,
    chatState: UiState<Unit>,
    showExitChattingRoomDialog: Boolean,
    setExitChattingRoomDialog: (Boolean) -> Unit,
    onChatTextChanged: (String) -> Unit,
    onSearchTextChanged: (String) -> Unit,
    popBackStack: () -> Unit,
) {
    val localConfiguration = LocalConfiguration.current
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var textFieldHeight by remember { mutableStateOf(0.dp) }
    var topBarHeight by remember { mutableStateOf(0.dp) }
    val focusManager = LocalFocusManager.current
    val listState = rememberLazyListState()
    val backgroundColor = Brush.verticalGradient(
        listOf(
            Color(0xFF0E1B45),
            Color(0xFF7C849F),
        ),
    )

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                if (showExitChattingRoomDialog) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                        Dialog(onDismissRequest = { setExitChattingRoomDialog(false) }) {
                            Card(shape = RoundedCornerShape(10.dp)) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .background(BaekyoungTheme.colors.white)
                                        .padding(vertical = 16.dp, horizontal = 20.dp),
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.exit_chattingroom_dialog_title),
                                        style = BaekyoungTheme.typography.labelBold.copy(fontSize = 14.sp),
                                        modifier = Modifier.padding(bottom = 2.dp),
                                    )

                                    Text(
                                        text = stringResource(id = R.string.exit_chattingroom_dialog_description),
                                        style = BaekyoungTheme.typography.labelRegular.copy(fontSize = 10.sp),
                                        color = BaekyoungTheme.colors.red,
                                    )

                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 20.dp),
                                    ) {
                                        BaekyoungButton(
                                            text = string.cancel,
                                            buttonColor = BaekyoungTheme.colors.grayF2,
                                            textColor = BaekyoungTheme.colors.black,
                                            onButtonClick = { setExitChattingRoomDialog(false) },
                                            modifier = Modifier.weight(1f),
                                        )

                                        BaekyoungButton(
                                            text = string.exit,
                                            textColor = BaekyoungTheme.colors.white,
                                            buttonColor = BaekyoungTheme.colors.red,
                                            onButtonClick = { setExitChattingRoomDialog(false) },
                                            modifier = Modifier.weight(1f),
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    Column(
                        modifier = Modifier
                            .background(BaekyoungTheme.colors.white)
                            .width((localConfiguration.screenWidthDp.dp * 3) / 4),
                    ) {
                        Text(
                            text = "채팅방 서랍",
                            style = BaekyoungTheme.typography.contentBold,
                            modifier = Modifier.padding(20.dp),
                        )

                        BaekyoungRow(
                            titleTextId = R.string.gallery,
                            showRightArrow = true,
                            onClick = { },
                            modifier = Modifier.fillMaxWidth(),
                        )

                        BaekyoungRow(
                            titleTextId = R.string.file,
                            showRightArrow = true,
                            onClick = { },
                            modifier = Modifier.fillMaxWidth(),
                        )

                        BaekyoungRow(
                            titleTextId = R.string.link,
                            showRightArrow = true,
                            onClick = { },
                            modifier = Modifier.fillMaxWidth(),
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(BaekyoungTheme.colors.grayDC),
                        ) {
                            Text(
                                text = "채팅방 나가기",
                                color = BaekyoungTheme.colors.gray95,
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(vertical = 15.dp, horizontal = 10.dp)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                    ) { setExitChattingRoomDialog(true) },
                            )

                            Image(
                                painter = painterResource(id = R.drawable.ic_chatting_setting),
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(vertical = 15.dp, horizontal = 10.dp)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                    ) { },
                            )
                        }
                    }
                }
            },
        ) {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                Scaffold(contentWindowInsets = WindowInsets(0.dp)) { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .background(backgroundColor)
                            .addFocusCleaner(focusManager),
                    ) {
                        ConsultingChattingBackground()

                        Image(
                            painter = painterResource(id = R.drawable.ic_stars),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(top = 20.dp),
                        )

                        Image(
                            painter = painterResource(id = R.drawable.ic_chatting_background),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth(),
                        )

                        Image(
                            painter = painterResource(id = R.drawable.ic_yellow_stars),
                            contentDescription = null,
                            alpha = 0.5f,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 55.dp),
                        )

                        Image(
                            painter = painterResource(id = R.drawable.ic_mentor_character),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 70.dp),
                        )

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 125.dp),
                        ) {
                            Text(
                                text = "무엇이 궁금한가요?",
                                style = BaekyoungTheme.typography.labelRegular,
                                color = BaekyoungTheme.colors.white,
                            )

                            Image(
                                painter = painterResource(id = R.drawable.ic_marker),
                                contentDescription = null,
                            )
                        }

                        BaekyoungCenterTopBar(
                            titleTextId = string.chatting,
                            textColor = BaekyoungTheme.colors.white,
                            showSearchButton = true,
                            showDrawerButton = true,
                            searchText = searchText,
                            onSearchTextChanged = onSearchTextChanged,
                            onClickDrawerButton = {
                                coroutineScope.launch {
                                    drawerState.open()
                                }
                            },
                            clearSearchText = { onSearchTextChanged("") },
                            onClickBackButton = popBackStack,
                            modifier = Modifier.layout { measurable, constraints ->
                                val placeable = measurable.measure(constraints)

                                topBarHeight = placeable.height.toDp()

                                layout(placeable.width, placeable.height) {
                                    placeable.placeRelative(0, 0)
                                }
                            },
                        )

                        LazyColumn(
                            state = listState,
                            verticalArrangement = Arrangement.spacedBy(20.dp),
                            contentPadding = PaddingValues(horizontal = 25.dp),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = topBarHeight, bottom = 220.dp),
                        ) {
                            items(items = chatLog) { message ->
                                val speechBubbleType = when (message.role) {
                                    ChattingRole.USER -> SpeechBubbleType.AI_USER
                                    ChattingRole.ASSISTANT -> SpeechBubbleType.AI_CHAT
                                    ChattingRole.SYSTEM, ChattingRole.FUNCTION -> return@items
                                }

                                BaekyoungSpeechBubble(
                                    type = speechBubbleType,
                                    text = message.content,
                                )
                            }
                        }

                        when (chatState) {
                            is UiState.Loading -> ChattingLoader(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(bottom = 135.dp),
                            )

                            else -> Unit
                        }

                        BaekyoungChatTextField(
                            chatText = chatText,
                            onTextChanged = onChatTextChanged,
                            sendMessage = {},
                            textColor = BaekyoungTheme.colors.black,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                                .layout { measurable, constraints ->
                                    val placeable = measurable.measure(constraints)

                                    textFieldHeight = placeable.height.toDp() + 16.dp

                                    layout(placeable.width, placeable.height) {
                                        placeable.placeRelative(0, 0)
                                    }
                                },
                        )
                    }
                }
            }
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
