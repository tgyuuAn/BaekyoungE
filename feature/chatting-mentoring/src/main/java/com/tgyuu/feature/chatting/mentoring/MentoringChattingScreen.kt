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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tgyuu.common.util.UiState
import com.tgyuu.common.util.addFocusCleaner
import com.tgyuu.designsystem.R.string
import com.tgyuu.designsystem.component.BaekyoungCenterTopBar
import com.tgyuu.designsystem.component.BaekyoungChatTextField
import com.tgyuu.designsystem.component.BaekyoungDialog
import com.tgyuu.designsystem.component.BaekyoungRow
import com.tgyuu.designsystem.component.BaekyoungSpeechBubble
import com.tgyuu.designsystem.component.ChattingLoader
import com.tgyuu.designsystem.component.SpeechBubbleType
import com.tgyuu.designsystem.theme.BaekyoungTheme
import com.tgyuu.domain.usecase.chatting.SearchResult
import com.tgyuu.model.auth.UserInformation
import com.tgyuu.model.chatting.MentoringMessage
import kotlinx.coroutines.launch

@Composable
internal fun MentoringChattingRoute(
    userId: String,
    roomId: String,
    popBackStack: () -> Unit,
    viewModel: MentoringChattingViewModel = hiltViewModel(),
) {
    val chatText by viewModel.chatText.collectAsStateWithLifecycle()
    val chatLog = viewModel.chatLog.toList()
    val searchText by viewModel.searchText.collectAsStateWithLifecycle()
    val chatState by viewModel.chatState.collectAsStateWithLifecycle()
    val (showExitChattingRoomDialog, setExitChattingRoomDialog) = remember { mutableStateOf(false) }
    val userInformation by viewModel.userInformation.collectAsStateWithLifecycle()
    val isFirstPage by viewModel.isFirstPage.collectAsStateWithLifecycle()
    val searchResult by viewModel.searchResult.collectAsStateWithLifecycle()
    val searchMode by viewModel.searchMode.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.apply {
            setRoomId(roomId)
            subscribeMessages()
        }
    }

    MentoringChattingScreen(
        chatText = chatText,
        searchText = searchText,
        chatLog = chatLog,
        chatState = chatState,
        userInformation = userInformation,
        isMentor = (userId == roomId.split("-")[0]),
        isFirstPage = isFirstPage,
        searchResult = searchResult,
        searchMode = searchMode,
        showExitChattingRoomDialog = showExitChattingRoomDialog,
        setExitChattingRoomDialog = setExitChattingRoomDialog,
        getPreviousMessages = viewModel::getPreviousMessages,
        onChatTextChanged = viewModel::setChatText,
        onSearchTextChanged = viewModel::setSearchText,
        onSearchExecuted = viewModel::onSearchExecuted,
        setSearchMode = viewModel::setSearchMode,
        sendMessage = viewModel::sendMessage,
        popBackStack = popBackStack,
    )
}

@Composable
internal fun MentoringChattingScreen(
    chatText: String,
    searchText: String,
    chatLog: List<MentoringMessage>,
    chatState: UiState<Unit>,
    userInformation: UserInformation,
    isMentor: Boolean,
    isFirstPage: Boolean,
    showExitChattingRoomDialog: Boolean,
    searchResult: SearchResult,
    searchMode: Boolean,
    setExitChattingRoomDialog: (Boolean) -> Unit,
    getPreviousMessages: () -> Unit,
    onChatTextChanged: (String) -> Unit,
    onSearchTextChanged: (String) -> Unit,
    onSearchExecuted: (Int?) -> Unit,
    setSearchMode: (Boolean) -> Unit,
    sendMessage: () -> Unit,
    popBackStack: () -> Unit,
) {
    val localConfiguration = LocalConfiguration.current
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var textFieldHeight by remember { mutableStateOf(0.dp) }
    var topBarHeight by remember { mutableStateOf(0.dp) }
    val focusManager = LocalFocusManager.current
    val listState = rememberLazyListState()
    var previousChatTime by remember { mutableStateOf("") }
    var previousSearchResult: SearchResult by remember { mutableStateOf(SearchResult()) }

    LaunchedEffect(searchResult) {
        if (searchResult.initialMatch != null) {
            listState.scrollToItem(searchResult.initialMatch?.first ?: (chatLog.size - 1))
            previousSearchResult = searchResult
            return@LaunchedEffect
        }
    }

    LaunchedEffect(chatLog.size) {
        if (previousChatTime != (chatLog.lastOrNull()?.createdAt ?: "")) {
            coroutineScope.launch {
                listState.scrollToItem(chatLog.size)
                previousChatTime = chatLog.last().createdAt
            }
        }
    }

    LaunchedEffect(listState.firstVisibleItemIndex) {
        if (listState.firstVisibleItemIndex <= 7 && !isFirstPage) {
            getPreviousMessages()
        }
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                if (showExitChattingRoomDialog) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                        BaekyoungDialog(
                            onDismissRequest = { setExitChattingRoomDialog(false) },
                            title = stringResource(id = R.string.exit_chattingroom_dialog_title),
                            description = stringResource(id = R.string.exit_chattingroom_dialog_description),
                            leftButtonText = stringResource(string.cancel),
                            rightButtonText = stringResource(string.exit),
                            rightButtonColor = BaekyoungTheme.colors.black,
                            onLeftButtonClick = { setExitChattingRoomDialog(false) },
                            onRightButtonClick = { setExitChattingRoomDialog(false) },
                        )
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
                            .background(
                                Brush.verticalGradient(
                                    listOf(Color(0xFF0E1B45), Color(0xFF7C849F)),
                                ),
                            )
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

                        if (isMentor) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_mentor_character),
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(bottom = 70.dp),
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.ic_mentee_character),
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(bottom = 58.dp),
                            )
                        }

                        BaekyoungCenterTopBar(
                            titleTextId = string.chatting,
                            textColor = BaekyoungTheme.colors.white,
                            showSearchButton = true,
                            showDrawerButton = true,
                            searchText = searchText,
                            onSearchExcuted = { onSearchExecuted(it) },
                            setSearchMode = {
                                setSearchMode(!searchMode)
                                if (searchMode) {
                                    previousSearchResult = SearchResult()
                                }
                            },
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

                        when (chatState) {
                            is UiState.Loading -> ChattingLoader(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(bottom = 135.dp),
                            )

                            else -> {
                                if (isMentor) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(6.dp),
                                        modifier = Modifier
                                            .align(Alignment.BottomCenter)
                                            .padding(bottom = 128.dp),
                                    ) {
                                        Text(
                                            text = "도와주세요 !",
                                            style = BaekyoungTheme.typography.labelRegular,
                                            color = BaekyoungTheme.colors.white,
                                        )

                                        Image(
                                            painter = painterResource(id = R.drawable.ic_marker),
                                            contentDescription = null,
                                        )
                                    }
                                } else {
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
                                }
                            }
                        }

                        LazyColumn(
                            state = listState,
                            verticalArrangement = Arrangement.spacedBy(20.dp),
                            contentPadding = PaddingValues(horizontal = 25.dp),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = topBarHeight, bottom = textFieldHeight + 20.dp),
                        ) {
                            itemsIndexed(items = chatLog) { idx, message ->
                                val speechBubbleType =
                                    if (message.fromUserId == userInformation.userId) {
                                        SpeechBubbleType.MENTOR_MENTI_USER
                                    } else {
                                        SpeechBubbleType.MENTOR_MENTI_OPPONENT
                                    }

                                val styledText = if (searchResult.initialMatch?.first == idx) {
                                    highlightSearchResults(message.content, searchResult.initialMatch!!.second)
                                } else {
                                    AnnotatedString(message.content)
                                }

                                BaekyoungSpeechBubble(
                                    type = speechBubbleType,
                                    text = styledText,
                                )
                            }
                        }

                        BaekyoungChatTextField(
                            chatText = chatText,
                            onTextChanged = onChatTextChanged,
                            sendMessage = { sendMessage() },
                            searchMode = searchMode,
                            searchResult = searchResult,
                            onSearchExecuted = { index -> onSearchExecuted(index) },
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

fun highlightSearchResults(text: String, ranges: List<IntRange>): AnnotatedString {
    return buildAnnotatedString {
        ranges.forEach { range ->
            append(text.substring(0, range.first))
            withStyle(style = SpanStyle(background = Color.Black, color = Color.White)) {
                append(text.substring(range.first, range.last + 1))
            }
            append(text.substring(range.last + 1))
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
