package com.tgyuu.baekyoung_i.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tgyuu.baekyoung_i.R.drawable
import com.tgyuu.baekyoung_i.R.string
import com.tgyuu.baekyoung_i.auth.component.ButtonWithShadow
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun AuthRoute(navigateToSignUp: () -> Unit) {
    AuthScreen(
        navigateToSignUp = navigateToSignUp,
    )
}

@Composable
fun AuthScreen(
    navigateToSignUp: () -> Unit,
) {
    val gradientColor = Brush.verticalGradient(
        listOf(
            BaekyoungTheme.colors.white,
            BaekyoungTheme.colors.blueF5FF
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientColor)
    ) {
        Image(
            painter = painterResource(id = drawable.ic_cheer_up_baekgyoung),
            contentDescription = stringResource(id = string.cheer_up_baekgyoung_description),
            modifier = Modifier.align(Alignment.TopEnd)
        )

        Text(
            text = stringResource(id = string.welcome_ment),
            style = BaekyoungTheme.typography.titleBold,
            color = BaekyoungTheme.colors.blueDD,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 20.dp, top = 16.dp)
        )

        Text(
            text = stringResource(id = string.login),
            style = BaekyoungTheme.typography.titleBold.copy(fontSize = 20.sp),
            color = BaekyoungTheme.colors.blueF8,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 20.dp, top = 16.dp)
                .clickable { navigateToSignUp() }
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .wrapContentHeight()
                .fillMaxWidth()
                .shadow(
                    elevation = 20.dp,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                )
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(BaekyoungTheme.colors.white),
        ) {
            Text(
                text = stringResource(id = string.sign_up),
                style = BaekyoungTheme.typography.contentRegular.copy(fontSize = 20.sp),
                color = BaekyoungTheme.colors.blueF8,
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 27.dp)
            ) {
                ButtonWithShadow(
                    drawableId = drawable.ic_naver,
                    contentDescription = string.naver_description,
                )

                Spacer(modifier = Modifier.size(49.dp))

                ButtonWithShadow(
                    drawableId = drawable.ic_google,
                    contentDescription = string.google_description,
                )
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ButtonWithShadow(
                    drawableId = drawable.ic_kakao,
                    contentDescription = string.kakao_description,
                )

                Spacer(modifier = Modifier.size(49.dp))

                ButtonWithShadow(
                    drawableId = drawable.ic_facebook,
                    contentDescription = string.facebook_description,
                )

                Spacer(modifier = Modifier.size(49.dp))

                ButtonWithShadow(
                    drawableId = drawable.ic_apple,
                    contentDescription = string.apple_description,
                )
            }

            Divider(
                color = BaekyoungTheme.colors.grayAC,
                thickness = 5.dp,
                modifier = Modifier
                    .padding(top = 60.dp, bottom = 15.dp)
                    .width(150.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
    }
}