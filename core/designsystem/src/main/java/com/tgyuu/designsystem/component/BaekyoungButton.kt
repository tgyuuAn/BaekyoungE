package com.tgyuu.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
fun BaekyoungButton(
    @StringRes text: Int,
    buttonColor: Color = BaekyoungTheme.colors.blueD7,
    textColor: Color = BaekyoungTheme.colors.white,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(buttonColor)
            .clickable { onButtonClick() },
    ) {
        Text(
            text = stringResource(id = text),
            style = BaekyoungTheme.typography.contentBold,
            color = textColor,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 12.dp),
        )
    }
}