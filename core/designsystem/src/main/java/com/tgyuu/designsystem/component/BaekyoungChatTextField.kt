package com.tgyuu.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
    searchMode: Boolean = false,
    searchResult: Triple<Int?, Int?, Int?> = Triple(null, null, null),
    onTextChanged: (String) -> Unit,
    onSearchExcuted: (Int?) -> Unit = {},
    sendMessage: () -> Unit,
    textColor: Color = BaekyoungTheme.colors.black,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    BasicTextField(
        value = chatText,
        onValueChange = onTextChanged,
        textStyle = BaekyoungTheme.typography.contentRegular.copy(
            color = textColor,
        ),
        readOnly = searchMode,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
        ),
        cursorBrush = SolidColor(BaekyoungTheme.colors.white),
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = BaekyoungTheme.colors.white.copy(alpha = if (searchMode) 0.4f else 0.93f),
                shape = RoundedCornerShape(3.dp),
            ),
    ) { innerTextField ->
        Box(modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp)) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .fillMaxWidth(),
            ) {
                innerTextField()

                if (!searchMode) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_send_message),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(start = 10.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            ) { sendMessage() },
                    )
                } else {
                    Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_up),
                            alpha = if (searchResult.second != null) 1f else 0.4f,
                            contentDescription = null,
                            modifier = Modifier
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                ) {
                                    if (searchResult.second != null) {
                                        onSearchExcuted(searchResult.second)
                                    }
                                },
                        )

                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_down),
                            alpha = if (searchResult.third != null) 1f else 0.4f,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                ) {
                                    if (searchResult.third != null) {
                                        onSearchExcuted(searchResult.third)
                                    }
                                },
                        )
                    }
                }
            }
        }
    }
}
