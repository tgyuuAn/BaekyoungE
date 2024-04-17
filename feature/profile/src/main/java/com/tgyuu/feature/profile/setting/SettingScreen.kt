package com.tgyuu.feature.profile.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tgyuu.common.util.UiState
import com.tgyuu.designsystem.component.BaekyoungCenterTopBar
import com.tgyuu.designsystem.component.Loader
import com.tgyuu.designsystem.theme.BaekyoungTheme
import com.tgyuu.feature.profile.R
import com.tgyuu.feature.profile.setting.component.SettingRow
import com.tgyuu.model.auth.UserInformation

@Composable
internal fun SettingRoute(
    popBackStack: () -> Unit,
    viewModel: SettingViewModel = hiltViewModel(),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val userInformationState by viewModel.userInformation.collectAsStateWithLifecycle()

    SettingScreen(
        userInformationState = userInformationState,
        snackbarHostState = snackbarHostState,
        popBackStack = popBackStack,
    )
}

@Composable
fun SettingScreen(
    userInformationState: UiState<UserInformation>,
    snackbarHostState: SnackbarHostState,
    popBackStack: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (userInformationState) {
            is UiState.Loading -> Loader(modifier = Modifier.fillMaxSize())
            is UiState.Success -> {
                val userInformation = userInformationState.data

                Scaffold(
                    contentWindowInsets = WindowInsets(0.dp),
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                ) { paddingValues ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize()
                            .background(BaekyoungTheme.colors.white),
                    ) {
                        BaekyoungCenterTopBar(
                            titleTextId = R.string.my_account,
                            showBackButton = true,
                            onClickBackButton = popBackStack,
                        )

                        Box(modifier = Modifier.padding(top = 25.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_user_default),
                                contentDescription = null,
                                modifier = Modifier.align(Alignment.TopCenter),
                            )

                            Image(
                                painter = painterResource(id = R.drawable.ic_camera),
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(end = 3.dp, top = 32.dp),
                            )
                        }

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(20.dp),
                        )

                        SettingRow(
                            titleTextId = R.string.change_nickname,
                            contentText = userInformation.nickName,
                            showContentText = true,
                            showRightArrow = true,
                            onClick = { },
                        )

                        SettingRow(
                            titleTextId = R.string.change_gender,
                            contentText = userInformation.gender,
                            showContentText = true,
                            showRightArrow = true,
                            onClick = { },
                        )

                        SettingRow(
                            titleTextId = R.string.change_major,
                            contentText = userInformation.major,
                            showContentText = true,
                            showRightArrow = true,
                            onClick = { },
                        )

                        SettingRow(
                            titleTextId = R.string.change_grade,
                            contentText = "${userInformation.grade}학년",
                            showContentText = true,
                            showRightArrow = true,
                            onClick = { },
                        )

                        HorizontalDivider(
                            thickness = 5.dp,
                            color = BaekyoungTheme.colors.grayF0,
                        )

                        SettingRow(
                            titleTextId = R.string.open_source_license,
                            showContentText = false,
                            showRightArrow = true,
                            onClick = { },
                        )

                        SettingRow(
                            titleTextId = R.string.privacy_policy,
                            showContentText = false,
                            showRightArrow = true,
                            onClick = { },
                        )

                        HorizontalDivider(
                            thickness = 5.dp,
                            color = BaekyoungTheme.colors.grayF0,
                        )

                        SettingRow(
                            titleTextId = R.string.logout,
                            showContentText = false,
                            showRightArrow = true,
                            onClick = { },
                        )

                        SettingRow(
                            titleTextId = R.string.withdrawal,
                            showContentText = false,
                            showRightArrow = true,
                            titleTextColor = Color.Red,
                            onClick = { },
                        )
                    }
                }
            }
            else -> Unit
        }
    }
}
