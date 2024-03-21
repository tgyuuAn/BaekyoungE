package com.tgyuu.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
fun BaekyoungTopBar(
    @StringRes titleTextId: Int,
    textColor: Color = BaekyoungTheme.colors.black,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
            .background(Color.Transparent)
    ) {
    }
}