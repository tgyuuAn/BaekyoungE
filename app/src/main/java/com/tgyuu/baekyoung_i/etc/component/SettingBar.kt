package com.tgyuu.baekyoung_i.etc.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tgyuu.baekyoung_i.R
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun SettingBar(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = text,
            style = BaekyoungTheme.typography.contentNormal,
            color = BaekyoungTheme.colors.black,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 40.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_right_arrow_purple),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 15.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewSettingBar() {
    SettingBar(text = "로그아웃")
}