package com.tgyuu.designsystem.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tgyuu.designsystem.R
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
fun BaekyoungTopBar(
    @StringRes titleTextId: Int,
    @DrawableRes titleImageId: Int? = null,
    @StringRes contentDescriptionId: Int? = null,
    textColor: Color = BaekyoungTheme.colors.black,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(20.dp)
        ) {
            if (titleImageId != null && contentDescriptionId != null) {
                Image(
                    painter = painterResource(id = titleImageId),
                    contentDescription = stringResource(contentDescriptionId),
                )
            }

            Text(
                text = stringResource(id = titleTextId),
                style = BaekyoungTheme.typography.labelBold.copy(fontSize = 13.sp),
                color = textColor,
            )
        }
    }
}

@Preview("타이틀 이미지가 있는 경우")
@Composable
fun PreviewBaekyongTopBar() {
    BaekyoungTheme {
        Box(
            modifier = Modifier
                .padding(20.dp)
                .background(BaekyoungTheme.colors.white)
        ) {
            BaekyoungTopBar(
                titleTextId = R.string.consulting,
                titleImageId = R.drawable.ic_consulting_note,
                contentDescriptionId = R.string.content_description_test,
            )
        }
    }
}

@Preview("타이틀 이미지가 없는 경우")
@Composable
fun PreviewBaekyongTopBarWithoutImage() {
    BaekyoungTheme {
        Box(
            modifier = Modifier
                .padding(20.dp)
                .background(BaekyoungTheme.colors.white)
        ) {
            BaekyoungTopBar(
                titleTextId = R.string.consulting,
            )
        }
    }
}