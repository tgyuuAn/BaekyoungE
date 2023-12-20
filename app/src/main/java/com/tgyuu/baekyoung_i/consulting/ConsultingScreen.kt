package com.tgyuu.baekyoung_i.consulting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tgyuu.baekyoung_i.R
import com.tgyuu.baekyoung_i.consulting.component.ConsultingTextField
import com.tgyuu.baekyoung_i.main.navigation.TopLevelDestination.CONSULTING
import com.tgyuu.designsystem.component.BaekyoungTopAppBar
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun ConsultingRoute() {
    ConsultingScreen()
}

@Composable
fun ConsultingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BaekyoungTheme.colors.white)
    ) {

        Image(
            painterResource(id = R.drawable.ic_consulting_bbugong),
            contentDescription = null,
            alpha = 0.3F,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 56.dp)
        )

        Image(
            painterResource(id = R.drawable.ic_consulting_baekyoung),
            contentDescription = null,
            alpha = 0.3F,
            modifier = Modifier
                .align(Alignment.BottomEnd)
        )

        Column {
            BaekyoungTopAppBar(CONSULTING.titleTextId)

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 40.dp)
            ) {
                Text(
                    text = "상담정보 입력",
                    style = BaekyoungTheme.typography.contentNormal.copy(fontSize = 24.sp),
                    color = BaekyoungTheme.colors.gray95,
                    textAlign = TextAlign.Start,
                )

                ConsultingTextField(
                    title = "학과",
                    value = "",
                    onValueChanged = {},
                )

                ConsultingTextField(
                    title = "학년",
                    value = "",
                    onValueChanged = {},
                    modifier = Modifier.padding(top = 20.dp),
                )

                Box(
                    modifier = Modifier
                        .padding(top = 90.dp)
                        .fillMaxWidth()
                        .size(height = 60.dp, width = 270.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(BaekyoungTheme.colors.blueF8)
                ) {
                    Text(
                        text = "상담",
                        textAlign = TextAlign.Center,
                        style = BaekyoungTheme.typography.contentBig,
                        color = BaekyoungTheme.colors.white,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}