package com.tgyuu.baekyounge.etc.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun SettingTitleBar(
    title: String,
    @DrawableRes leadIconId: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
    ) {
        Image(
            painter = painterResource(id = leadIconId),
            contentDescription = null,
            modifier = Modifier.padding(start = 15.dp),
        )

        Text(
            text = title,
            style = BaekyoungTheme.typography.contentNormal,
            color = BaekyoungTheme.colors.black,
            modifier = Modifier.padding(start = 15.dp),
        )
    }
}
