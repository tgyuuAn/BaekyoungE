package com.tgyuu.baekyoung_i.auth

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tgyuu.baekyoung_i.R.drawable
import com.tgyuu.baekyoung_i.R.string
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun AuthRoute(navigateToHome: () -> Unit) {
    AuthScreen(
        navigateToHome = navigateToHome,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    navigateToHome: () -> Unit,
) {
    BottomSheetScaffold(
        sheetContainerColor = BaekyoungTheme.colors.white,
        sheetContentColor = BaekyoungTheme.colors.white,
        sheetContent = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(vertical = 15.dp)
                    .fillMaxSize(),
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
                        contentDescription = string.sign_up
                    )

                    Spacer(modifier = Modifier.size(49.dp))

                    ButtonWithShadow(
                        drawableId = drawable.ic_google,
                        contentDescription = string.sign_up
                    )
                }
            }
        },
        content = {
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
                )
            }
        },
    )
}

@Composable
private fun ButtonWithShadow(
    @DrawableRes drawableId: Int,
    @StringRes contentDescription: Int,
    onClickButton: () -> Unit = {},
) {
    Box {
        Spacer(
            modifier = Modifier
                .size(49.dp)
                .offset(x = 4.dp, y = 4.dp)
                .shadow(elevation = 5.dp, shape = CircleShape, clip = true),
        )

        Image(
            painter = painterResource(id = drawableId),
            contentDescription = stringResource(id = contentDescription),
            modifier = Modifier
                .size(49.dp)
                .clickable { onClickButton() },
        )
    }
}