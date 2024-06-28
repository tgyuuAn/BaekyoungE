package com.tgyuu.feature.mentoring.mentor

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tgyuu.common.R.drawable
import com.tgyuu.common.util.UiState
import com.tgyuu.designsystem.component.BaekyoungButton
import com.tgyuu.designsystem.component.BaekyoungCenterTopBar
import com.tgyuu.designsystem.component.Loader
import com.tgyuu.designsystem.theme.BaekyoungTheme
import com.tgyuu.model.chatting.JoinChat

@Composable
internal fun MentoringMentorRoute(
    navigateToMentoringChatting: (String, String) -> Unit,
    popBackStack: () -> Unit,
    viewModel: MentoringMentorViewModel = hiltViewModel(),
) {
    val isRegistered by viewModel.isRegistered.collectAsStateWithLifecycle()
    val chattingRooms by viewModel.chattingRooms.collectAsStateWithLifecycle()

    MentoringMentorScreen(
        isRegistered = isRegistered,
        chattingRooms = chattingRooms,
        registerMentorInfo = viewModel::registerMentorInfo,
        deleteMentorInfo = viewModel::deleteMentorInfo,
        navigateToMentoringChatting = navigateToMentoringChatting,
        popBackStack = popBackStack,
    )
}

@Composable
fun MentoringMentorScreen(
    isRegistered: Boolean,
    chattingRooms: UiState<List<JoinChat>>,
    registerMentorInfo: () -> Unit,
    deleteMentorInfo: () -> Unit,
    navigateToMentoringChatting: (String, String) -> Unit,
    popBackStack: () -> Unit,
) {
    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        containerColor = BaekyoungTheme.colors.grayF5,
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        when (chattingRooms) {
            UiState.Loading -> Loader(modifier = Modifier.fillMaxSize())

            is UiState.Error -> {
                // Todo
            }

            is UiState.Success -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(BaekyoungTheme.colors.white),
                ) {
                    item {
                        BaekyoungCenterTopBar(
                            titleTextId = R.string.chatting_room,
                            showBackButton = true,
                            onClickBackButton = popBackStack,
                        )
                    }

                    if (isRegistered) {
                        item {
                            Text(
                                text = "진행 중인 채팅방",
                                style = BaekyoungTheme.typography.contentBold,
                                modifier = Modifier.padding(horizontal = 20.dp),
                            )
                        }

                        items(chattingRooms.data) {
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                colors = CardDefaults.cardColors(containerColor = BaekyoungTheme.colors.white),
                                onClick = {
                                    navigateToMentoringChatting(
                                        it.mentorId,
                                        it.roomId,
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp)
                                    .border(
                                        width = 1.dp,
                                        shape = RoundedCornerShape(10.dp),
                                        color = BaekyoungTheme.colors.grayDC,
                                    ),
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 10.dp, vertical = 15.dp),
                                ) {
                                    Image(
                                        painter = painterResource(id = drawable.ic_user_default),
                                        contentDescription = null,
                                        modifier = Modifier.size(40.dp),
                                    )

                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(4.dp),
                                        modifier = Modifier.padding(start = 10.dp),
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.fillMaxWidth(),
                                        ) {
                                            Text(
                                                text = it.menteeNickName,
                                                style = BaekyoungTheme.typography.contentBold,
                                                color = BaekyoungTheme.colors.black,
                                            )

                                            Text(
                                                text = "멘티",
                                                style = BaekyoungTheme.typography.labelRegular,
                                                color = BaekyoungTheme.colors.gray95,
                                                modifier = Modifier.padding(start = 5.dp),
                                            )

                                            Spacer(modifier = Modifier.weight(1f))

                                            Text(
                                                text = it.getFormattedLastSentTime(),
                                                style = BaekyoungTheme.typography.labelRegular.copy(
                                                    fontSize = 10.sp,
                                                ),
                                                color = BaekyoungTheme.colors.gray95,
                                                modifier = Modifier.align(Alignment.Top),
                                            )
                                        }

                                        Text(
                                            text = it.lastChatting,
                                            style = BaekyoungTheme.typography.labelRegular,
                                            color = BaekyoungTheme.colors.gray95,
                                        )
                                    }
                                }
                            }
                        }
                    } else {
                        item {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize(),
                            ) {
                                Text(
                                    text = "멘토 정보가 등록 되어있지 않아요.\n" +
                                        "멘토 정보를 등록하고 멘토 활동을\n" +
                                        "시작 해보세요! ",
                                    style = BaekyoungTheme.typography.contentRegular,
                                    color = BaekyoungTheme.colors.gray95,
                                    modifier = Modifier.padding(bottom = 20.dp),
                                )

                                BaekyoungButton(
                                    text = "멘토 등록 하러가기",
                                    onButtonClick = { /*TODO*/ },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
