package com.tgyuu.feature.mentoring

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tgyuu.designsystem.component.BaekyoungButton
import com.tgyuu.designsystem.component.BaekyoungTopBar
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun MentoringRoute(
    navigateToMentoringMentor: () -> Unit,
    navigateToMentoringMentee: () -> Unit,
    viewModel: MentoringViewModel = hiltViewModel(),
) {
    val selectedRule by viewModel.selectedRule.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    MentoringScreen(
        selectedRule = selectedRule,
        setSelectedRule = viewModel::setSelectedRule,
        snackbarHostState = snackbarHostState,
        navigateToMentoringMentor = navigateToMentoringMentor,
        navigateToMentoringMentee = navigateToMentoringMentee,
    )
}

@Composable
fun MentoringScreen(
    selectedRule: MentorMenteeRule,
    setSelectedRule: (MentorMenteeRule) -> Unit,
    snackbarHostState: SnackbarHostState,
    navigateToMentoringMentor: () -> Unit,
    navigateToMentoringMentee: () -> Unit,
) {
    Scaffold(
        topBar = { BaekyoungTopBar(titleTextId = R.string.mentoring) },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = BaekyoungTheme.colors.grayF5,
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            HorizontalDivider(color = BaekyoungTheme.colors.grayDC)

            Text(
                text = "역할 선택하기",
                style = BaekyoungTheme.typography.labelBold.copy(fontSize = 13.sp),
                color = BaekyoungTheme.colors.black,
                modifier = Modifier.padding(start = 20.dp, bottom = 30.dp),
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 4.dp,
                        color = if (selectedRule == MentorMenteeRule.MENTOR) {
                            BaekyoungTheme.colors.blueEDFF.copy(alpha = 0.4f)
                        } else {
                            Color.Transparent
                        },
                        shape = RoundedCornerShape(20.dp),
                    )
                    .background(BaekyoungTheme.colors.white)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) { setSelectedRule(MentorMenteeRule.MENTOR) },
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_mentor),
                    contentDescription = null,
                    modifier = Modifier.padding(top = 20.dp),
                )

                Text(
                    text = "멘토로 입장하기",
                    style = BaekyoungTheme.typography.labelBold.copy(fontSize = 13.sp),
                    color = BaekyoungTheme.colors.black,
                )

                Text(
                    text = "도움이 필요한 후배들에게 조언을 해주세요.",
                    style = BaekyoungTheme.typography.labelBold.copy(fontSize = 13.sp),
                    color = BaekyoungTheme.colors.gray95,
                    modifier = Modifier.padding(bottom = 20.dp),
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 4.dp,
                        color = if (selectedRule == MentorMenteeRule.MENTEE) {
                            BaekyoungTheme.colors.blueEDFF.copy(alpha = 0.4f)
                        } else {
                            Color.Transparent
                        },
                        shape = RoundedCornerShape(20.dp),
                    )
                    .background(BaekyoungTheme.colors.white)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) { setSelectedRule(MentorMenteeRule.MENTEE) },
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_mentee),
                    contentDescription = null,
                    modifier = Modifier.padding(top = 20.dp),
                )

                Text(
                    text = "멘티로 입장하기",
                    style = BaekyoungTheme.typography.labelBold.copy(fontSize = 13.sp),
                    color = BaekyoungTheme.colors.black,
                )

                Text(
                    text = "선배님들에게 조언을 받으세요.",
                    style = BaekyoungTheme.typography.labelBold.copy(fontSize = 13.sp),
                    color = BaekyoungTheme.colors.gray95,
                    modifier = Modifier.padding(bottom = 20.dp),
                )
            }

            AnimatedVisibility(
                visible = selectedRule != MentorMenteeRule.NOTHING,
                enter = slideInVertically(initialOffsetY = { it / 2 }) + fadeIn(),
                exit = slideOutVertically() + fadeOut(),
            ) {
                BaekyoungButton(
                    text = R.string.go_to_mentoring,
                    onButtonClick = {
                        when (selectedRule) {
                            MentorMenteeRule.MENTEE -> navigateToMentoringMentee()
                            MentorMenteeRule.MENTOR -> navigateToMentoringMentor()
                            MentorMenteeRule.NOTHING -> Unit
                        }
                    },
                    buttonColor = BaekyoungTheme.colors.black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                )
            }
        }
    }
}
