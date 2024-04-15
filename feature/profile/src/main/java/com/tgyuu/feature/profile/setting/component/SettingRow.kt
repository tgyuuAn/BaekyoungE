package com.tgyuu.feature.profile.setting.component

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tgyuu.designsystem.theme.BaekyoungTheme
import com.tgyuu.feature.profile.R

@Composable
internal fun SettingRow(
    @StringRes titleTextId: Int,
    titleTextColor: Color = BaekyoungTheme.colors.black56,
    showContentText: Boolean = false,
    contentText: String,
    showRightArrow: Boolean = false,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
        ) {
            Text(
                text = stringResource(id = titleTextId),
                style = BaekyoungTheme.typography.labelRegular.copy(fontSize = 13.sp),
                color = titleTextColor,
            )

            Text(
                text = if (showContentText) contentText else "",
                style = BaekyoungTheme.typography.labelRegular.copy(fontSize = 13.sp),
                color = BaekyoungTheme.colors.grayD0,
            )
        }

        if (showRightArrow) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_right_gray),
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 20.dp),
            )
        }
    }
}
