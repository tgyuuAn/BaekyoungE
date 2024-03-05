package com.tgyuu.baekyoung_i.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tgyuu.baekyoung_i.R.drawable
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
                    text = "회원가입을 진행해 주세요",
                    style = BaekyoungTheme.typography.contentRegular.copy(fontSize = 20.sp),
                    color = BaekyoungTheme.colors.blueF8,
                )

                Row(modifier = Modifier.padding(vertical = 27.dp)) {

                }
            }
        },
        content = {
            val gradientColor = Brush.verticalGradient(
                listOf(
                    BaekyoungTheme.colors.white,
                    BaekyoungTheme.colors.blueF8
                )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gradientColor)
            ) {
                Image(
                    painter = painterResource(id = drawable.ic_cheer_up_baekgyoung),
                    contentDescription = "꿈을 찾는 것을 응원하는 백경이",
                    modifier = Modifier.align(Alignment.TopEnd)
                )

                Text(text = "백경이와 함께 꿈을\n 찾으러 가보자",
                    style = BaekyoungTheme.typography.titleNormal)
            }
        },
    )
}