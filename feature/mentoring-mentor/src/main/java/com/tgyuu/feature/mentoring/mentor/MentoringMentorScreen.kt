package com.tgyuu.feature.mentoring.mentor

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tgyuu.designsystem.component.BaekyoungCenterTopBar
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun MentoringMentorRoute(
    popBackStack: () -> Unit,
    viewModel: MentoringMentorViewModel = hiltViewModel(),
) {
    val checked by viewModel.checked.collectAsStateWithLifecycle()

    MentoringMentorScreen(
        checked = checked,
        registerMentorInfo = viewModel::registerMentorInfo,
        popBackStack = popBackStack,
    )
}

@Composable
fun MentoringMentorScreen(
    checked: Boolean,
    registerMentorInfo: () -> Unit,
    popBackStack: () -> Unit,
) {
    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        containerColor = BaekyoungTheme.colors.grayF5,
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp),
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

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(20.dp),
            ) {
                AnimatedContent(targetState = checked) {
                    if (it) {
                        Text(
                            text = "멘티 요청 받는 중",
                            style = BaekyoungTheme.typography.contentBold,
                        )
                    } else {
                        Text(
                            text = "멘티 요청 받지 않는 중",
                            style = BaekyoungTheme.typography.contentBold,
                        )
                    }
                }

                Switch(
                    checked = checked,
                    onCheckedChange = { registerMentorInfo() },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = BaekyoungTheme.colors.blueFF,
                        checkedTrackColor = BaekyoungTheme.colors.blue5FF,
                        uncheckedThumbColor = BaekyoungTheme.colors.white,
                        uncheckedTrackColor = BaekyoungTheme.colors.blue5FF,
                        uncheckedBorderColor = Color.Transparent,
                    ),
                )
            }
        }
    }
}
