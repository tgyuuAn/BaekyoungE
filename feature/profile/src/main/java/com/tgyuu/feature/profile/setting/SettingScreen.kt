package com.tgyuu.feature.profile.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sd.lib.compose.wheel_picker.FVerticalWheelPicker
import com.sd.lib.compose.wheel_picker.FWheelPickerState
import com.sd.lib.compose.wheel_picker.rememberFWheelPickerState
import com.tgyuu.common.util.UiState
import com.tgyuu.designsystem.component.BaekyoungCenterTopBar
import com.tgyuu.designsystem.component.BaekyoungDialog
import com.tgyuu.designsystem.component.BaekyoungModalBottomSheet
import com.tgyuu.designsystem.component.BaekyoungRow
import com.tgyuu.designsystem.component.Loader
import com.tgyuu.designsystem.theme.BaekyoungTheme
import com.tgyuu.feature.profile.R
import com.tgyuu.feature.profile.setting.component.SettingTextField
import com.tgyuu.model.auth.UserInformation
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
internal fun SettingRoute(
    popBackStack: () -> Unit,
    navigateToAuth: () -> Unit,
    viewModel: SettingViewModel = hiltViewModel(),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val userInformationState by viewModel.userInformation.collectAsStateWithLifecycle()
    val newNickname by viewModel.newNickname.collectAsStateWithLifecycle()
    val newMajor by viewModel.newMajor.collectAsStateWithLifecycle()
    val gradePickerState = rememberFWheelPickerState()

    LaunchedEffect(gradePickerState) {
        snapshotFlow { gradePickerState.currentIndex }
            .collect { grade ->
                viewModel.setNewGrade(grade + 1)
            }
    }

    SettingScreen(
        newNickname = newNickname,
        newMajor = newMajor,
        userInformationState = userInformationState,
        snackbarHostState = snackbarHostState,
        gradePickerState = gradePickerState,
        eventFlow = viewModel.eventFlow,
        popBackStack = popBackStack,
        onNewNicknameChanged = viewModel::setNewNickname,
        clearNewNickname = viewModel::clearNewNickname,
        onNewMajorChanged = viewModel::setNewMajor,
        clearNewMajor = viewModel::clearNewMajor,
        updateNewNickname = viewModel::updateNewNickname,
        updateNewMajor = viewModel::updateNewMajor,
        updateNewGrade = viewModel::updateNewGrade,
        logoutKakao = viewModel::logoutKakao,
        withdrawalKakao = viewModel::withdrawalKakao,
        navigateToAuth = navigateToAuth,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    newNickname: String,
    newMajor: String,
    userInformationState: UiState<UserInformation>,
    snackbarHostState: SnackbarHostState,
    gradePickerState: FWheelPickerState,
    eventFlow: SharedFlow<SettingViewModel.SettingEvent>,
    popBackStack: () -> Unit,
    onNewNicknameChanged: (String) -> Unit,
    clearNewNickname: () -> Unit,
    onNewMajorChanged: (String) -> Unit,
    clearNewMajor: () -> Unit,
    updateNewNickname: () -> Unit,
    updateNewMajor: () -> Unit,
    updateNewGrade: () -> Unit,
    logoutKakao: () -> Unit,
    withdrawalKakao: () -> Unit,
    navigateToAuth: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (userInformationState) {
            is UiState.Loading -> Loader(modifier = Modifier.fillMaxSize())
            is UiState.Success -> {
                val userInformation = userInformationState.data
                var bottomSheetType by remember { mutableStateOf(BottomSheetType.INIT) }
                var showBottomSheet by remember { mutableStateOf(false) }
                val sheetState = rememberModalBottomSheetState()
                val coroutineScope = rememberCoroutineScope()
                val snackBarCoroutineScope = rememberCoroutineScope()
                var showLogoutDialog by remember { mutableStateOf(false) }
                var showWithdrawalDialog by remember { mutableStateOf(false) }

                LaunchedEffect(true) {
                    eventFlow.collectLatest { event ->
                        when (event) {
                            is SettingViewModel.SettingEvent.UpdateSuccess ->
                                coroutineScope.launch {
                                    sheetState.hide()
                                }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        showBottomSheet = false
                                    }

                                    snackBarCoroutineScope.launch {
                                        snackbarHostState.showSnackbar("업데이트가 완료되었습니다!")
                                    }
                                }

                            is SettingViewModel.SettingEvent.UpdateFailed ->
                                snackBarCoroutineScope.launch {
                                    snackbarHostState.showSnackbar(event.message)
                                }

                            is SettingViewModel.SettingEvent.LogoutFailed ->
                                snackBarCoroutineScope.launch {
                                    snackbarHostState.showSnackbar(event.message)
                                }

                            is SettingViewModel.SettingEvent.LogoutSuccess -> {
                                showLogoutDialog = false
                                showWithdrawalDialog = false
                                navigateToAuth()
                            }
                        }
                    }
                }

                if (showLogoutDialog) {
                    BaekyoungDialog(
                        onDismissRequest = { showLogoutDialog = false },
                        title = stringResource(id = R.string.logout_dialog_title),
                        leftButtonText = stringResource(R.string.cancel),
                        rightButtonText = stringResource(R.string.logout),
                        rightButtonColor = BaekyoungTheme.colors.black,
                        onLeftButtonClick = { showLogoutDialog = false },
                        onRightButtonClick = { logoutKakao() },
                    )
                }

                if (showWithdrawalDialog) {
                    BaekyoungDialog(
                        onDismissRequest = { showWithdrawalDialog = false },
                        title = stringResource(id = R.string.withdrawal_dialog_title),
                        description = stringResource(id = R.string.withdrawal_dialog_description),
                        leftButtonText = stringResource(R.string.cancel),
                        rightButtonText = stringResource(R.string.withdrawal2),
                        onLeftButtonClick = { showWithdrawalDialog = false },
                        onRightButtonClick = { withdrawalKakao() },
                    )
                }

                Scaffold(
                    contentWindowInsets = WindowInsets(0.dp),
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                ) { paddingValues ->
                    if (showBottomSheet) {
                        when (bottomSheetType) {
                            BottomSheetType.INIT -> Unit

                            BottomSheetType.CHANGE_GRADE -> {
                                BaekyoungModalBottomSheet(
                                    onDissmissRequest = {
                                        showBottomSheet = false
                                        clearNewNickname()
                                    },
                                    sheetState = sheetState,
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(12.dp),
                                        modifier = Modifier.fillMaxWidth(),
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.please_input_grade),
                                            style = BaekyoungTheme.typography.labelBold.copy(
                                                fontSize = 14.sp,
                                            ),
                                            color = BaekyoungTheme.colors.black,
                                            modifier = Modifier.padding(top = 20.dp),
                                        )

                                        FVerticalWheelPicker(
                                            modifier = Modifier.width(50.dp),
                                            count = 4,
                                            state = gradePickerState,
                                        ) { grade ->
                                            Text(
                                                text = "${grade + 1} 학년",
                                                style = BaekyoungTheme.typography.labelBold.copy(
                                                    fontSize = 14.sp,
                                                ),
                                                color = BaekyoungTheme.colors.black,
                                            )
                                        }

                                        Button(
                                            onClick = { updateNewGrade() },
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
                                                text = stringResource(id = R.string.complete),
                                                style = BaekyoungTheme.typography.labelBold,
                                                color = BaekyoungTheme.colors.white,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .align(Alignment.CenterVertically)
                                                    .padding(vertical = 8.dp),
                                            )
                                        }
                                    }
                                }
                            }

                            BottomSheetType.CHANGE_MAJOR -> {
                                BaekyoungModalBottomSheet(
                                    onDissmissRequest = {
                                        showBottomSheet = false
                                        clearNewMajor()
                                    },
                                    sheetState = sheetState,
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(12.dp),
                                        modifier = Modifier.fillMaxWidth(),
                                    ) {
                                        val focusRequester = remember { FocusRequester() }

                                        LaunchedEffect(true) {
                                            focusRequester.requestFocus()
                                        }

                                        Text(
                                            text = stringResource(id = R.string.please_input_major),
                                            style = BaekyoungTheme.typography.labelBold.copy(
                                                fontSize = 14.sp,
                                            ),
                                            color = BaekyoungTheme.colors.black,
                                            modifier = Modifier.padding(top = 20.dp),
                                        )

                                        SettingTextField(
                                            text = newMajor,
                                            onTextChanged = onNewMajorChanged,
                                            onConfirm = { updateNewMajor() },
                                            hint = stringResource(id = R.string.hint_major),
                                            modifier = Modifier
                                                .padding(horizontal = 20.dp)
                                                .focusRequester(focusRequester),
                                        )

                                        Text(
                                            text = stringResource(id = R.string.change_it_again_at_any_time),
                                            style = BaekyoungTheme.typography.labelBold.copy(
                                                fontSize = 10.sp,
                                            ),
                                            color = BaekyoungTheme.colors.grayAC,
                                        )

                                        Button(
                                            onClick = { updateNewMajor() },
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
                                                text = stringResource(id = R.string.complete),
                                                style = BaekyoungTheme.typography.labelBold,
                                                color = BaekyoungTheme.colors.white,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .align(Alignment.CenterVertically)
                                                    .padding(vertical = 8.dp),
                                            )
                                        }
                                    }
                                }
                            }

                            BottomSheetType.CHANGE_NICKNAME -> {
                                BaekyoungModalBottomSheet(
                                    onDissmissRequest = { showBottomSheet = false },
                                    sheetState = sheetState,
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(12.dp),
                                        modifier = Modifier.fillMaxWidth(),
                                    ) {
                                        val focusRequester = remember { FocusRequester() }

                                        LaunchedEffect(true) {
                                            focusRequester.requestFocus()
                                        }

                                        Text(
                                            text = stringResource(id = R.string.please_input_nickname),
                                            style = BaekyoungTheme.typography.labelBold.copy(
                                                fontSize = 14.sp,
                                            ),
                                            color = BaekyoungTheme.colors.black,
                                            modifier = Modifier.padding(top = 20.dp),
                                        )

                                        SettingTextField(
                                            text = newNickname,
                                            onTextChanged = onNewNicknameChanged,
                                            onConfirm = { updateNewNickname() },
                                            hint = stringResource(id = R.string.hint_nickname),
                                            modifier = Modifier
                                                .padding(horizontal = 20.dp)
                                                .focusRequester(focusRequester),
                                        )

                                        Text(
                                            text = stringResource(id = R.string.change_it_again_at_any_time),
                                            style = BaekyoungTheme.typography.labelBold.copy(
                                                fontSize = 10.sp,
                                            ),
                                            color = BaekyoungTheme.colors.grayAC,
                                        )

                                        Button(
                                            onClick = { updateNewNickname() },
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
                                                text = stringResource(id = R.string.complete),
                                                style = BaekyoungTheme.typography.labelBold,
                                                color = BaekyoungTheme.colors.white,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .align(Alignment.CenterVertically)
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

                        BaekyoungRow(
                            titleTextId = R.string.change_nickname,
                            contentText = userInformation.nickName,
                            showContentText = true,
                            showRightArrow = true,
                            onClick = {
                                clearNewNickname()
                                bottomSheetType = BottomSheetType.CHANGE_NICKNAME
                                if (!showBottomSheet) {
                                    showBottomSheet = true
                                }
                            },
                        )

                        BaekyoungRow(
                            titleTextId = R.string.change_major,
                            contentText = userInformation.major,
                            showContentText = true,
                            showRightArrow = true,
                            onClick = {
                                clearNewMajor()
                                bottomSheetType = BottomSheetType.CHANGE_MAJOR
                                if (!showBottomSheet) {
                                    showBottomSheet = true
                                }
                            },
                        )

                        BaekyoungRow(
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

                        BaekyoungRow(
                            titleTextId = R.string.open_source_license,
                            showContentText = false,
                            showRightArrow = true,
                            onClick = { },
                        )

                        BaekyoungRow(
                            titleTextId = R.string.privacy_policy,
                            showContentText = false,
                            showRightArrow = true,
                            onClick = { },
                        )

                        HorizontalDivider(
                            thickness = 5.dp,
                            color = BaekyoungTheme.colors.grayF0,
                        )

                        BaekyoungRow(
                            titleTextId = R.string.logout,
                            showContentText = false,
                            showRightArrow = true,
                            onClick = { showLogoutDialog = true },
                        )

                        BaekyoungRow(
                            titleTextId = R.string.withdrawal,
                            showContentText = false,
                            showRightArrow = true,
                            titleTextColor = Color.Red,
                            onClick = { showWithdrawalDialog = true },
                        )
                    }
                }
            }

            else -> Unit
        }
    }
}

enum class BottomSheetType {
    INIT, CHANGE_NICKNAME, CHANGE_MAJOR, CHANGE_GRADE
}
