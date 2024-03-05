package com.tgyuu.baekyoung_i.auth.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
internal fun ButtonWithShadow(
    @DrawableRes drawableId: Int,
    @StringRes contentDescription: Int,
    onClickButton: () -> Unit = {},
) {
    Image(
        painter = painterResource(id = drawableId),
        contentDescription = stringResource(id = contentDescription),
        modifier = Modifier
            .size(49.dp)
            .shadow(elevation = 6.dp, shape = CircleShape)
            .clip(CircleShape)
            .clickable { onClickButton() },
    )
}