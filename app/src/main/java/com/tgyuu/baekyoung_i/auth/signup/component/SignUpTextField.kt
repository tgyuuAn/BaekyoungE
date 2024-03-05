package com.tgyuu.baekyoung_i.auth.signup.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.tgyuu.designsystem.theme.BaekyoungTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun SignUpTextField(
    @StringRes title: Int,
    @StringRes hint: Int,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = title),
            style = BaekyoungTheme.typography.contentBold,
            color = BaekyoungTheme.colors.black56,
            modifier = Modifier.padding(start = 5.dp),
        )

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = BaekyoungTheme.typography.labelRegular.copy(
                color = BaekyoungTheme.colors.black56
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            modifier = Modifier
                .background(color = BaekyoungTheme.colors.white, shape = RoundedCornerShape(10.dp))
                .border(
                    width = 1.dp,
                    color = BaekyoungTheme.colors.grayD0,
                    shape = RoundedCornerShape(10.dp),
                ),
        ){ innerTextField ->
            Box(modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp)) {
                if (value.isEmpty()) {
                    Text(
                        text = stringResource(id = hint),
                        color = BaekyoungTheme.colors.grayAC,
                        style = BaekyoungTheme.typography.labelRegular,
                        modifier = Modifier.align(Alignment.CenterStart),
                    )
                }

                Box(modifier = Modifier.align(Alignment.CenterStart)) {
                    innerTextField()
                }
            }
        }
    }
}