package com.tgyuu.feature.auth.signup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tgyuu.common.util.addFocusCleaner
import com.tgyuu.designsystem.R.drawable
import com.tgyuu.designsystem.R.string
import com.tgyuu.designsystem.component.BaekgyoungClouds
import com.tgyuu.designsystem.component.BaekyoungButton
import com.tgyuu.designsystem.theme.BaekyoungTheme
import com.tgyuu.feature.auth.R
import com.tgyuu.feature.auth.signup.SignUpViewModel.Gender
import com.tgyuu.feature.auth.signup.component.SignUpTextField
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun SignUpRoute(
    userId: String,
    navigateToHome: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    val major by viewModel.major.collectAsStateWithLifecycle()
    val grade by viewModel.grade.collectAsStateWithLifecycle()
    val gender by viewModel.gender.collectAsStateWithLifecycle()
    val nickname by viewModel.nickname.collectAsStateWithLifecycle()
    val isSignUpSuccess by viewModel.isSignUpSuccess.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(isSignUpSuccess) {
        if (isSignUpSuccess) {
            snackbarHostState.showSnackbar("회원가입에 성공하였습니다!")
        }
    }

    LaunchedEffect(true) {
        viewModel.setUserId(userId)

        viewModel.signUpEventFlow.collectLatest { event ->
            when (event) {
                is SignUpViewModel.SignUpEvent.SignUpSuccess -> navigateToHome()

                is SignUpViewModel.SignUpEvent.SignUpFailed ->
                    snackbarHostState.showSnackbar(event.message)
            }
        }
    }

    SignUpScreen(
        nickname = nickname,
        major = major,
        grade = grade,
        gender = gender,
        snackbarHostState = snackbarHostState,
        isSignUpSuccess = isSignUpSuccess,
        signUp = viewModel::signUp,
        onNicknameChanged = viewModel::setNickname,
        onMajorChanged = viewModel::setMajor,
        onGradeChanged = viewModel::setGrade,
        onGenderChanged = viewModel::setGender,
    )
}

@Composable
internal fun SignUpScreen(
    nickname: String,
    major: String,
    grade: String,
    gender: Gender,
    snackbarHostState: SnackbarHostState,
    isSignUpSuccess: Boolean,
    signUp: () -> Unit,
    onNicknameChanged: (String) -> Unit,
    onMajorChanged: (String) -> Unit,
    onGradeChanged: (String) -> Unit,
    onGenderChanged: (Gender) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val localConfiguration = LocalConfiguration.current
    var showBottomSheet by remember { mutableStateOf(false) }
    val animateOffset by animateDpAsState(
        targetValue = if (!isSignUpSuccess) 0.dp else -ANIMATION_OFFSET.dp,
        animationSpec = tween(
            DROP_CAMERA_DURATION_MILLIS,
            HIDE_SIGN_UP_UI_DURATION_MILLIS,
            CubicBezierEasing(0.3f, 0.3f, 0.6f, 0.9f),
        ),
    )

    val backgroundColor = Brush.verticalGradient(
        listOf(
            BaekyoungTheme.colors.blueEDFF,
            BaekyoungTheme.colors.white,
        ),
    )

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(backgroundColor)
                .addFocusCleaner(
                    focusManager = focusManager,
                    doOnClear = { showBottomSheet = false },
                ),
        ) {
            BaekgyoungClouds(animateOffset = animateOffset)

            Image(
                painter = painterResource(id = drawable.ic_sea),
                contentDescription = stringResource(id = string.sea_description),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(
                        y = localConfiguration.screenHeightDp.dp +
                                ANIMATION_OFFSET.dp - SEA_IMAGE_HEIGHT.dp,
                    )
                    .graphicsLayer {
                        this.translationY = animateOffset.toPx()
                    },
            )

            Image(
                painter = painterResource(id = drawable.ic_main_baekgyoung),
                contentDescription = stringResource(id = string.main_baekgyoung_description),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .offset(
                        y = localConfiguration.screenHeightDp.dp +
                                ANIMATION_OFFSET.dp - SEA_IMAGE_HEIGHT.dp - 43.dp,
                    )
                    .graphicsLayer {
                        this.translationY = animateOffset.toPx()
                    },
            )

            AnimatedVisibility(
                visible = !isSignUpSuccess,
                exit = fadeOut(tween(HIDE_SIGN_UP_UI_DURATION_MILLIS)),
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BaekyoungTheme.colors.white)
                        .padding(start = 24.dp, end = 24.dp),
                ) {
                    Text(
                        text = stringResource(id = R.string.welcome_message),
                        style = BaekyoungTheme.typography.titleBold,
                        color = BaekyoungTheme.colors.black,
                    )

                    Text(
                        text = stringResource(id = R.string.please_input_nickname_and_gender),
                        style = BaekyoungTheme.typography.labelBold,
                        color = BaekyoungTheme.colors.black,
                        modifier = Modifier.padding(top = 10.dp),
                    )

                    SignUpTextField(
                        title = R.string.nickname,
                        hint = R.string.nickname_hint,
                        value = nickname,
                        onValueChange = onNicknameChanged,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 75.dp),
                    )

                    Text(
                        text = stringResource(id = R.string.gender),
                        style = BaekyoungTheme.typography.contentBold,
                        color = BaekyoungTheme.colors.black,
                        modifier = Modifier.padding(start = 5.dp, top = 20.dp, bottom = 4.dp),
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))
                            .background(
                                color = BaekyoungTheme.colors.white,
                                shape = RoundedCornerShape(10.dp),
                            )
                            .border(
                                width = 1.dp,
                                color = BaekyoungTheme.colors.grayD0,
                                shape = RoundedCornerShape(10.dp),
                            )
                            .clickable { showBottomSheet = !showBottomSheet },
                    ) {
                        Text(
                            text = gender.displayName,
                            style = BaekyoungTheme.typography.labelRegular,
                            color = BaekyoungTheme.colors.black56,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(horizontal = 12.dp, vertical = 10.dp),
                        )

                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_down_auth),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(horizontal = 12.dp, vertical = 6.dp),
                        )
                    }

                    SignUpTextField(
                        title = R.string.major,
                        hint = R.string.major_hint,
                        value = major,
                        onValueChange = onMajorChanged,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                    )

                    SignUpTextField(
                        title = R.string.grade,
                        hint = R.string.grade_hint,
                        value = grade,
                        onValueChange = onGradeChanged,
                        keyboardType = KeyboardType.Number,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                    )

                    BaekyoungButton(
                        text = stringResource(R.string.confirm),
                        onButtonClick = {
                            if (!showBottomSheet) {
                                signUp()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 70.dp),
                    )
                }
            }

            AnimatedVisibility(
                visible = showBottomSheet,
                modifier = Modifier.align(Alignment.BottomCenter),
                enter = expandVertically(),
                exit = shrinkVertically(),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 20.dp,
                            shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
                        )
                        .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                        .background(BaekyoungTheme.colors.white),
                ) {
                    Text(
                        text = stringResource(id = R.string.male),
                        style = BaekyoungTheme.typography.labelRegular,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 15.dp)
                            .clickable {
                                onGenderChanged(Gender.MALE)
                                showBottomSheet = !showBottomSheet
                            },
                    )

                    HorizontalDivider(color = BaekyoungTheme.colors.grayD0)

                    Text(
                        text = stringResource(id = R.string.female),
                        style = BaekyoungTheme.typography.labelRegular,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 15.dp)
                            .clickable {
                                onGenderChanged(Gender.FEMALE)
                                showBottomSheet = !showBottomSheet
                            },
                    )
                }
            }
        }
    }
}

private val ANIMATION_OFFSET = 1360
private val SEA_IMAGE_HEIGHT = 166
private val DROP_CAMERA_DURATION_MILLIS = 3000
private val HIDE_SIGN_UP_UI_DURATION_MILLIS = 1000
