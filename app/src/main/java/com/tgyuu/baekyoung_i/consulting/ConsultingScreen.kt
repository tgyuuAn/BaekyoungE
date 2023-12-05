package com.tgyuu.baekyoung_i.consulting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tgyuu.baekyoung_i.R
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
            .background(BaekyoungTheme.colors.blue5FF)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            BaekyoungTopAppBar(CONSULTING.titleTextId)
        }

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
    }
}