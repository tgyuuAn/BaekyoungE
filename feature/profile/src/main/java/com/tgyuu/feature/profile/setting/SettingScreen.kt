package com.tgyuu.feature.profile.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tgyuu.common.util.UiState
import com.tgyuu.designsystem.component.BaekyoungCenterTopBar
import com.tgyuu.designsystem.component.Loader
import com.tgyuu.designsystem.theme.BaekyoungTheme
import com.tgyuu.feature.profile.R
import com.tgyuu.feature.profile.setting.component.SettingRow
import com.tgyuu.feature.profile.setting.component.SettingTextField
import com.tgyuu.model.auth.UserInformation
import kotlinx.coroutines.launch

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

@OptIn(ExperimentalMaterial3Api::class)
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

                var bottomSheetType by remember { mutableStateOf(BottomSheetType.INIT) }
                var showBottomSheet by remember { mutableStateOf(false) }
                val coroutineScope = rememberCoroutineScope()
                val sheetState = rememberModalBottomSheetState()

                Scaffold(
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                ) { paddingValues ->
                    if (showBottomSheet) {
                        when (bottomSheetType) {
                            BottomSheetType.INIT -> Unit
                            BottomSheetType.CHANGE_GENDER -> {
                                ModalBottomSheet(
                                    onDismissRequest = { showBottomSheet = false },
                                    sheetState = sheetState,
                                ) {

                                }
                            }

                            BottomSheetType.CHANGE_GRADE -> {
                                ModalBottomSheet(
                                    onDismissRequest = { showBottomSheet = false },
                                    sheetState = sheetState,
                                ) {

                                }
                            }

                            BottomSheetType.CHANGE_MAJOR -> {
                                ModalBottomSheet(
                                    onDismissRequest = { showBottomSheet = false },
                                    sheetState = sheetState,
                                ) {

                                }
                            }

                            BottomSheetType.CHANGE_NICKNAME -> {
                                ModalBottomSheet(
                                    onDismissRequest = { showBottomSheet = false },
                                    sheetState = sheetState,
                                    containerColor = BaekyoungTheme.colors.white,
                                    dragHandle = {},
                                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(12.dp),
                                        modifier = Modifier.fillMaxWidth(),
                                    ) {
                                        Text(
                                            text = "새로운 닉네임을 입력해주세요.",
                                            style = BaekyoungTheme.typography.labelBold.copy(
                                                fontSize = 14.sp,
                                            ),
                                            color = BaekyoungTheme.colors.black,
                                            modifier = Modifier.padding(top = 20.dp),
                                        )

                                        SettingTextField(
                                            text = "",
                                            onTextChanged = { },
                                            onConfirm = { },
                                            hint = "최대 12글자까지 입력할 수 있어요!",
                                        )

                                        Text(
                                            text = "언제든지 다시 바꿀 수 있어요.",
                                            style = BaekyoungTheme.typography.labelBold.copy(
                                                fontSize = 10.sp,
                                            ),
                                            color = BaekyoungTheme.colors.grayD0,
                                        )

                                        Button(
                                            onClick = {
                                                coroutineScope.launch { sheetState.hide() }
                                                    .invokeOnCompletion {
                                                        if (!sheetState.isVisible) {
                                                            showBottomSheet = false
                                                        }
                                                    }
                                            },
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = BaekyoungTheme.colors.gray95,
                                            ),
                                            shape = RoundedCornerShape(5.dp),
                                            modifier = Modifier.padding(
                                                bottom = 20.dp,
                                                start = 20.dp,
                                                end = 20.dp,
                                            ),
                                        ) {
                                            Text(
                                                text = "완료",
                                                style = BaekyoungTheme.typography.labelBold,
                                                color = BaekyoungTheme.colors.white,
                                                textAlign = TextAlign.Center,  // horizontal center of the text
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .align(Alignment.CenterVertically) //vertical center of the Text composabl
                                                    .padding(vertical = 8.dp),
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

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
                            onClick = {
                                bottomSheetType = BottomSheetType.CHANGE_NICKNAME
                                if (!showBottomSheet) {
                                    showBottomSheet = true
                                }
                            },
                        )

                        SettingRow(
                            titleTextId = R.string.change_gender,
                            contentText = userInformation.gender,
                            showContentText = true,
                            showRightArrow = true,
                            onClick = {
                                bottomSheetType = BottomSheetType.CHANGE_GENDER
                                if (!showBottomSheet) {
                                    showBottomSheet = true
                                }
                            },
                        )

                        SettingRow(
                            titleTextId = R.string.change_major,
                            contentText = userInformation.major,
                            showContentText = true,
                            showRightArrow = true,
                            onClick = {
                                bottomSheetType = BottomSheetType.CHANGE_MAJOR
                                if (!showBottomSheet) {
                                    showBottomSheet = true
                                }
                            },
                        )

                        SettingRow(
                            titleTextId = R.string.change_grade,
                            contentText = "${userInformation.grade}학년",
                            showContentText = true,
                            showRightArrow = true,
                            onClick = {
                                bottomSheetType = BottomSheetType.CHANGE_GRADE
                                if (!showBottomSheet) {
                                    showBottomSheet = true
                                }
                            },
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

enum class BottomSheetType {
    INIT, CHANGE_NICKNAME, CHANGE_MAJOR, CHANGE_GRADE, CHANGE_GENDER
}
