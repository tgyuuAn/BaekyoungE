package com.tgyuu.baekyoung_i.auth.signup.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun SignUpTextField(
    @StringRes title: Int,
    @StringRes hint: Int,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = title),
            style = BaekyoungTheme.typography.contentBold,
            color = BaekyoungTheme.colors.black56,
        )
    }
}