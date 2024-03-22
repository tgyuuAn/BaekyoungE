package com.tgyuu.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.tgyuu.designsystem.R
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
fun BaekyoungChatTextField(
    chatText: String,
    onTextChanged: (String) -> Unit,
    sendMessage: () -> Unit,
    textColor: Color = BaekyoungTheme.colors.black,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    BasicTextField(
        value = chatText,
        onValueChange = onTextChanged,
        textStyle = BaekyoungTheme.typography.contentRegular.copy(
            color = textColor
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        cursorBrush = SolidColor(BaekyoungTheme.colors.white),
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            .background(
                color = BaekyoungTheme.colors.white.copy(alpha = 0.4f),
                shape = RoundedCornerShape(3.dp)
            ),
    ) { innerTextField ->
        Box(modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp)) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .fillMaxWidth()
            ) {
                innerTextField()

                Image(
                    painter = painterResource(id = R.drawable.ic_send_message),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(horizontal = 10.dp)
                        .clickable { sendMessage() }
                )
            }
        }
    }
}