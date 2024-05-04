package com.tgyuu.feature.mentoring

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tgyuu.designsystem.component.BaekyoungTopBar
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun MentoringRoute() {
    val snackbarHostState = remember { SnackbarHostState() }

    MentoringScreen(snackbarHostState = snackbarHostState)
}

@Composable
fun MentoringScreen(snackbarHostState: SnackbarHostState) {
    Scaffold(
        topBar = { BaekyoungTopBar(titleTextId = R.string.mentoring) },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = BaekyoungTheme.colors.grayF5,
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            Text(
                text = "역할 선택하기",
                style = BaekyoungTheme.typography.labelBold.copy(fontSize = 13.sp),
                color = BaekyoungTheme.colors.black,
                modifier = Modifier.padding(start = 20.dp, top = 35.dp, bottom = 30.dp),
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp))
                    .background(BaekyoungTheme.colors.white),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_mentor),
                    contentDescription = null,
                    modifier = Modifier.padding(top = 20.dp),
                )

                Text(
                    text = "멘토로 입장하기",
                    style = BaekyoungTheme.typography.labelBold.copy(fontSize = 13.sp),
                    color = BaekyoungTheme.colors.black,
                )

                Text(
                    text = "도움이 필요한 후배들에게 조언을 해주세요.",
                    style = BaekyoungTheme.typography.labelBold.copy(fontSize = 13.sp),
                    color = BaekyoungTheme.colors.gray95,
                    modifier = Modifier.padding(bottom = 20.dp),
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp))
                    .background(BaekyoungTheme.colors.white),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_mentee),
                    contentDescription = null,
                    modifier = Modifier.padding(top = 20.dp),
                )

                Text(
                    text = "멘티로 입장하기",
                    style = BaekyoungTheme.typography.labelBold.copy(fontSize = 13.sp),
                    color = BaekyoungTheme.colors.black,
                )

                Text(
                    text = "선배님들에게 조언을 받으세요.",
                    style = BaekyoungTheme.typography.labelBold.copy(fontSize = 13.sp),
                    color = BaekyoungTheme.colors.gray95,
                    modifier = Modifier.padding(bottom = 20.dp),
                )
            }
        }
    }
}
