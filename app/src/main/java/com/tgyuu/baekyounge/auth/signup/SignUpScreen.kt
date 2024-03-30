package com.tgyuu.baekyounge.auth.signup

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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tgyuu.baekyounge.R
import com.tgyuu.baekyounge.auth.signup.component.SignUpTextField
import com.tgyuu.common.util.addFocusCleaner
import com.tgyuu.designsystem.component.BaekgyoungClouds
import com.tgyuu.designsystem.component.BaekyoungButton
import com.tgyuu.designsystem.theme.BaekyoungTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun SignUpRoute(
    userId: String,
    navigateToHome: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    val major by viewModel.major.collectAsStateWithLifecycle()
    val grade by viewModel.grade.collectAsStateWithLifecycle()
    val nickname by viewModel.nickname.collectAsStateWithLifecycle()
    val isSignUpSuccess by viewModel.isSignUpSuccess.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.setUserId(userId)

        viewModel.signUpEventFlow.collectLatest { event ->
            when (event) {
                is SignUpViewModel.SignUpEvent.SignUpSuccess -> navigateToHome()
                is SignUpViewModel.SignUpEvent.SignUpFailed -> {}
            }
        }
    }

    SignUpScreen(
        nickname = nickname,
        major = major,
        grade = grade,
        isSignUpSuccess = isSignUpSuccess,
        signUp = viewModel::signUp,
        onNicknameChanged = viewModel::setNickname,
        onMajorChanged = viewModel::setMajor,
        onGradeChanged = viewModel::setGrade,
    )
}

@Composable
internal fun SignUpScreen(
    nickname: String,
    major: String,
    grade: String,
    isSignUpSuccess: Boolean,
    signUp: () -> Unit,
    onNicknameChanged: (String) -> Unit,
    onMajorChanged: (String) -> Unit,
    onGradeChanged: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val localConfiguration = LocalConfiguration.current
    var showSpinner by remember { mutableStateOf(false) }
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .addFocusCleaner(
                focusManager = focusManager,
                doOnClear = { showSpinner = false },
            ),
    ) {
        BaekgyoungClouds(animateOffset = animateOffset)

        Image(
            painter = painterResource(id = R.drawable.ic_sea),
            contentDescription = null,
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
            painter = painterResource(id = R.drawable.ic_main_baekgyoung),
            contentDescription = null,
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
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 24.dp, end = 24.dp, top = 30.dp),
                ) {
                    Text(
                        text = stringResource(id = R.string.welcome_message),
                        style = BaekyoungTheme.typography.titleBold,
                        color = BaekyoungTheme.colors.black56,
                        modifier = Modifier.padding(start = 5.dp, top = 16.dp),
                    )

                    Text(
                        text = stringResource(id = R.string.please_input_nickname_and_sex),
                        style = BaekyoungTheme.typography.labelBold,
                        color = BaekyoungTheme.colors.black56,
                        modifier = Modifier.padding(start = 5.dp, top = 16.dp),
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
                        text = stringResource(id = R.string.sex),
                        style = BaekyoungTheme.typography.contentBold,
                        color = BaekyoungTheme.colors.black56,
                        modifier = Modifier.padding(start = 5.dp, top = 16.dp),
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = BaekyoungTheme.colors.white,
                                shape = RoundedCornerShape(10.dp),
                            )
                            .border(
                                width = 1.dp,
                                color = BaekyoungTheme.colors.grayD0,
                                shape = RoundedCornerShape(10.dp),
                            )
                            .clickable { showSpinner = !showSpinner },
                    ) {
                        Text(
                            text = stringResource(id = R.string.male),
                            style = BaekyoungTheme.typography.labelRegular,
                            color = BaekyoungTheme.colors.black56,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(horizontal = 12.dp, vertical = 10.dp),
                        )

                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_down),
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
                            .padding(top = 16.dp),
                    )

                    SignUpTextField(
                        title = R.string.grade,
                        hint = R.string.grade_hint,
                        value = grade,
                        onValueChange = onGradeChanged,
                        keyboardType = KeyboardType.Number,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                    )
                }

                BaekyoungButton(
                    text = R.string.confirm,
                    onButtonClick = { signUp() },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 40.dp),
                )
            }
        }

        AnimatedVisibility(
            visible = showSpinner,
            modifier = Modifier.align(Alignment.BottomCenter),
            enter = expandVertically(),
            exit = shrinkVertically(),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                    .background(BaekyoungTheme.colors.white),
            ) {
                Text(
                    text = stringResource(id = R.string.male),
                    style = BaekyoungTheme.typography.labelRegular,
                    modifier = Modifier.padding(vertical = 15.dp),
                )

                HorizontalDivider(color = BaekyoungTheme.colors.grayD0)

                Text(
                    text = stringResource(id = R.string.female),
                    style = BaekyoungTheme.typography.labelRegular,
                    modifier = Modifier.padding(vertical = 15.dp),
                )
            }
        }
    }
}

private val ANIMATION_OFFSET = 1360
private val SEA_IMAGE_HEIGHT = 166
private val DROP_CAMERA_DURATION_MILLIS = 3000
private val HIDE_SIGN_UP_UI_DURATION_MILLIS = 1000
