package com.tgyuu.feature.storage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
internal fun StorageRoute() {
    StorageScreen()
}

@Composable
internal fun StorageScreen() {
    Scaffold(
        topBar = { BaekyoungTopBar(titleTextId = R.string.storage) },
        containerColor = BaekyoungTheme.colors.grayF5,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            HorizontalDivider(color = BaekyoungTheme.colors.grayDC)

            var showSpinner by remember { mutableStateOf(false) }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp),
            ) {
                Text(
                    text = "2024년",
                    style = BaekyoungTheme.typography.labelBold.copy(fontSize = 13.sp),
                    color = BaekyoungTheme.colors.gray95,
                )

                Image(
                    painter = painterResource(id = com.tgyuu.designsystem.R.drawable.ic_spinner_arrow),
                    contentDescription = null,
                    modifier = Modifier.clickable { showSpinner = !showSpinner },
                )
            }

            if (showSpinner) {
                LazyColumn(
                    modifier = Modifier
                        .padding(start = 20.dp, top = 5.dp)
                        .shadow(elevation = 10.dp, shape = RoundedCornerShape(8.dp))
                        .clip(RoundedCornerShape(8.dp))
                        .background(BaekyoungTheme.colors.white),
                ) {
                    item {
                        Spacer(modifier = Modifier.size(width = 80.dp, height = 100.dp))
                    }
                }
            }
        }
    }
}
