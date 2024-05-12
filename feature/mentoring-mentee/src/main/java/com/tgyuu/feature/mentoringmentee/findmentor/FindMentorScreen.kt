package com.tgyuu.feature.mentoringmentee.findmentor

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.tgyuu.designsystem.R.string
import com.tgyuu.designsystem.component.BaekyoungButton
import com.tgyuu.designsystem.component.BaekyoungCenterTopBar
import com.tgyuu.designsystem.theme.BaekyoungTheme
import com.tgyuu.feature.mentoring.mentee.R

@Composable
internal fun FindMentorRoute(
    popBackStack: () -> Unit,
    navigateToMentorChatting: () -> Unit,
) {
    FindMentorScreen(
        popBackStack = popBackStack,
        navigateToMentorChatting = navigateToMentorChatting,
    )
}

@Composable
fun FindMentorScreen(
    popBackStack: () -> Unit,
    navigateToMentorChatting: () -> Unit,
) {
    val (showEnterChattingRoomDialog, setEnterChattingRoomDialog) = remember { mutableStateOf(false) }
    var selectedMentor by remember { mutableStateOf("") }

    if (showEnterChattingRoomDialog) {
        Dialog(onDismissRequest = { setEnterChattingRoomDialog(false) }) {
            Card(shape = RoundedCornerShape(10.dp)) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(BaekyoungTheme.colors.white)
                        .padding(vertical = 16.dp, horizontal = 20.dp),
                ) {
                    Text(
                        text = "$selectedMentor 님과 대화를 시작하시겠어요?",
                        style = BaekyoungTheme.typography.contentBold,
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp),
                    ) {
                        BaekyoungButton(
                            text = string.cancel,
                            buttonColor = BaekyoungTheme.colors.grayF2,
                            textColor = BaekyoungTheme.colors.black,
                            onButtonClick = { setEnterChattingRoomDialog(false) },
                            modifier = Modifier.weight(1f),
                        )

                        BaekyoungButton(
                            text = string.confirm,
                            textColor = BaekyoungTheme.colors.white,
                            buttonColor = BaekyoungTheme.colors.black,
                            onButtonClick = {
                                setEnterChattingRoomDialog(false)
                                navigateToMentorChatting()
                            },
                            modifier = Modifier.weight(1f),
                        )
                    }
                }
            }
        }
    }

    Scaffold(
        containerColor = BaekyoungTheme.colors.grayF5,
        contentWindowInsets = WindowInsets(0.dp),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(BaekyoungTheme.colors.white),
        ) {
            BaekyoungCenterTopBar(
                titleTextId = R.string.find_mentor,
                showBackButton = true,
                onClickBackButton = popBackStack,
            )

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(horizontal = 20.dp),
            ) {
                items(listOf("종디기", "안태규")) { mentor ->
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = BaekyoungTheme.colors.white),
                        onClick = {
                            selectedMentor = mentor
                            setEnterChattingRoomDialog(true)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(20.dp),
                                color = BaekyoungTheme.colors.grayDC,
                            ),
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(20.dp),
                            modifier = Modifier.padding(20.dp),
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_user_default),
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp),
                                )

                                Text(
                                    text = mentor,
                                    style = BaekyoungTheme.typography.contentBold,
                                    color = BaekyoungTheme.colors.black,
                                    modifier = Modifier.padding(start = 20.dp),
                                )

                                Text(
                                    text = "멘토",
                                    style = BaekyoungTheme.typography.labelRegular,
                                    color = BaekyoungTheme.colors.gray95,
                                    modifier = Modifier.padding(start = 5.dp),
                                )

                                Spacer(modifier = Modifier.weight(1f))

                                Text(
                                    text = "오후 6:29",
                                    style = BaekyoungTheme.typography.labelRegular.copy(fontSize = 10.sp),
                                    color = BaekyoungTheme.colors.gray95,
                                    modifier = Modifier.align(Alignment.Top),
                                )
                            }

                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                                    Text(
                                        text = "회사",
                                        style = BaekyoungTheme.typography.labelBold,
                                        color = BaekyoungTheme.colors.black,
                                        modifier = Modifier.align(Alignment.Top),
                                    )

                                    Text(
                                        text = "|",
                                        style = BaekyoungTheme.typography.labelRegular.copy(
                                            fontSize = 10.sp,
                                        ),
                                        color = BaekyoungTheme.colors.gray95,
                                        modifier = Modifier.align(Alignment.Top),
                                    )

                                    Text(
                                        text = "삼성전자",
                                        style = BaekyoungTheme.typography.labelRegular.copy(
                                            fontSize = 10.sp,
                                        ),
                                        color = BaekyoungTheme.colors.gray95,
                                        modifier = Modifier.align(Alignment.Top),
                                    )
                                }

                                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                                    Text(
                                        text = "직무",
                                        style = BaekyoungTheme.typography.labelBold,
                                        color = BaekyoungTheme.colors.black,
                                        modifier = Modifier.align(Alignment.Top),
                                    )

                                    Text(
                                        text = "|",
                                        style = BaekyoungTheme.typography.labelRegular.copy(
                                            fontSize = 10.sp,
                                        ),
                                        color = BaekyoungTheme.colors.gray95,
                                        modifier = Modifier.align(Alignment.Top),
                                    )

                                    Text(
                                        text = "전산",
                                        style = BaekyoungTheme.typography.labelRegular.copy(
                                            fontSize = 10.sp,
                                        ),
                                        color = BaekyoungTheme.colors.gray95,
                                        modifier = Modifier.align(Alignment.Top),
                                    )
                                }
                            }
                        }
                    }
                }

                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .fillMaxWidth()
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            ) { },
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_re_search),
                            contentDescription = null,
                        )

                        Text(
                            text = "다시 찾으시겠어요?",
                            style = BaekyoungTheme.typography.labelRegular,
                            color = BaekyoungTheme.colors.black,
                        )
                    }
                }
            }
        }
    }
}
