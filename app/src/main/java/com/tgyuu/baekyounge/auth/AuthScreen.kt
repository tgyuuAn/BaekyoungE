package com.tgyuu.baekyounge.auth

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.tgyuu.baekyounge.R
import com.tgyuu.baekyounge.R.string
import com.tgyuu.baekyounge.auth.component.ButtonWithShadow
import com.tgyuu.designsystem.theme.BaekyoungTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
internal fun AuthRoute(
    navigateToSignUp: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel(),
) {
    val snackbarHostState = remember { SnackbarHostState() }

    AuthScreen(
        snackbarHostState = snackbarHostState,
        navigateToSignUp = navigateToSignUp,
    )
}

@Composable
fun AuthScreen(
    snackbarHostState: SnackbarHostState,
    navigateToSignUp: () -> Unit,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) {
    val context = LocalContext.current
    val gradientColor = Brush.verticalGradient(
        listOf(
            BaekyoungTheme.colors.white,
            BaekyoungTheme.colors.blueF5FF,
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
                .background(gradientColor),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_cheer_up_baekgyoung),
                contentDescription = stringResource(id = string.cheer_up_baekgyoung_description),
                modifier = Modifier.align(Alignment.TopEnd),
            )

            Text(
                text = stringResource(id = string.welcome_ment),
                style = BaekyoungTheme.typography.titleBold,
                color = BaekyoungTheme.colors.blueDD,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 20.dp, top = 16.dp),
            )

            Text(
                text = stringResource(id = string.login),
                style = BaekyoungTheme.typography.titleBold.copy(fontSize = 20.sp),
                color = BaekyoungTheme.colors.blueF8,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 20.dp, top = 16.dp)
                    .clickable { navigateToSignUp() },
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .shadow(
                        elevation = 20.dp,
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                    )
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(BaekyoungTheme.colors.white),
            ) {
                Text(
                    text = stringResource(id = string.sign_up),
                    style = BaekyoungTheme.typography.contentRegular.copy(fontSize = 20.sp),
                    color = BaekyoungTheme.colors.blueF8,
                    modifier = Modifier.padding(top = 15.dp),
                )

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 27.dp),
                ) {
                    ButtonWithShadow(
                        drawableId = R.drawable.ic_naver,
                        contentDescription = string.naver_description,
                    )

                    Spacer(modifier = Modifier.size(49.dp))

                    ButtonWithShadow(
                        drawableId = R.drawable.ic_google,
                        contentDescription = string.google_description,
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    ButtonWithShadow(
                        drawableId = R.drawable.ic_kakao,
                        contentDescription = string.kakao_description,
                        onClickButton = {
                            loginKakao(coroutineScope, snackbarHostState, context)
                        },
                    )

                    Spacer(modifier = Modifier.size(49.dp))

                    ButtonWithShadow(
                        drawableId = R.drawable.ic_facebook,
                        contentDescription = string.facebook_description,
                    )

                    Spacer(modifier = Modifier.size(49.dp))

                    ButtonWithShadow(
                        drawableId = R.drawable.ic_apple,
                        contentDescription = string.apple_description,
                    )
                }

                HorizontalDivider(
                    modifier = Modifier
                        .padding(top = 60.dp, bottom = 15.dp)
                        .width(150.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    thickness = 5.dp,
                    color = BaekyoungTheme.colors.grayAC,
                )
            }
        }
    }
}

private fun loginKakao(
    coroutineScope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    context: Context,
) {
    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            coroutineScope.launch { snackbarHostState.showSnackbar(error.toString()) }
        } else if (token != null) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    "로그인에 성공하였습니다." + token.toString(),
                )
            }
        }
    }

    if (!UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar("카카오톡을 실행할 수 없습니다.")
        }
        return
    }

    UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
        Log.d("test", "error : ${error.toString()} token : ${token.toString()}")

        if (error != null) {
            // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
            // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
            if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                return@loginWithKakaoTalk
            }

            // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
            return@loginWithKakaoTalk
        }

        coroutineScope.launch {
            snackbarHostState.showSnackbar(
                "로그인에 성공하였습니다." + token.toString(),
            )
        }
    }
}
