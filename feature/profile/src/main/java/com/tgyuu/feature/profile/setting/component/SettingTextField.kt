package com.tgyuu.feature.profile.setting.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tgyuu.designsystem.R
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun SettingTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    onConfirm: () -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    BasicTextField(
        value = text,
        onValueChange = onTextChanged,
        textStyle = BaekyoungTheme.typography.labelRegular.copy(fontSize = 13.sp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
        ),
        cursorBrush = SolidColor(BaekyoungTheme.colors.black),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                onConfirm()
            },
        ),
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = BaekyoungTheme.colors.white.copy(alpha = 0.4f),
                shape = RoundedCornerShape(3.dp),
            ),
    ) { innerTextField ->
        Box(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(BaekyoungTheme.colors.grayF2),
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .fillMaxWidth()
                    .padding(10.dp),
            ) {
                innerTextField()

                if (text.isEmpty()) {
                    Text(
                        text = hint,
                        color = BaekyoungTheme.colors.grayB4,
                        textAlign = TextAlign.Center,
                        style = BaekyoungTheme.typography.labelRegular.copy(fontSize = 13.sp),
                        modifier = Modifier.align(Alignment.CenterStart),
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.ic_text_clear),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(start = 10.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) { onConfirm() },
                )
            }
        }
    }
}

@Preview
@Composable
fun SettingTextFieldPreview() {
    BaekyoungTheme {
        SettingTextField(
            text = "",
            onTextChanged = {},
            onConfirm = { /*TODO*/ },
            hint = "최대 12글자",
        )
    }
}
