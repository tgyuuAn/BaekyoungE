package com.tgyuu.designsystem.component

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tgyuu.designsystem.R
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
fun BaekyoungCenterTopBar(
    @StringRes titleTextId: Int,
    searchText: String = "",
    showBackButton: Boolean = true,
    showSearchButton: Boolean = false,
    showDrawerButton: Boolean = false,
    textColor: Color = BaekyoungTheme.colors.black,
    onClickBackButton: () -> Unit = {},
    onClickDrawerButton: () -> Unit = {},
    onSearchTextChanged: (String) -> Unit = {},
    clearSearchText: () -> Unit = {},
    onSearchExcuted: (Int?) -> Unit = {},
    setSearchMode: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    var showSearchBar by remember { mutableStateOf(false) }
    val onShowSearchBarChanged = {
        showSearchBar = !showSearchBar
        setSearchMode()
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.Transparent),
    ) {
        if (showBackButton) {
            Image(
                painter = painterResource(
                    id = if (textColor == BaekyoungTheme.colors.black) {
                        R.drawable.ic_arrow_left_black
                    } else {
                        R.drawable.ic_arrow_left_white
                    },
                ),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(horizontal = 20.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) {
                        if (showSearchBar) {
                            onShowSearchBarChanged()
                            clearSearchText()
                        } else {
                            onClickBackButton()
                        }
                    },
            )
        }

        AnimatedContent(targetState = showSearchBar) { showSearchBar ->
            Box(
                modifier = modifier.fillMaxSize(),
            ) {
                if (showSearchBar) {
                    BasicTextField(
                        value = searchText,
                        onValueChange = onSearchTextChanged,
                        textStyle = BaekyoungTheme.typography.contentRegular.copy(color = textColor),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        cursorBrush = SolidColor(BaekyoungTheme.colors.white),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                Log.d("test", "텍스트필드 Action On Done 호출")
                                onSearchExcuted(null)
                                keyboardController?.hide()
                            },
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                            .padding(start = 50.dp, end = 20.dp)
                            .align(Alignment.Center)
                            .background(
                                color = BaekyoungTheme.colors.white.copy(alpha = 0.4f),
                                shape = RoundedCornerShape(10.dp),
                            ),
                    ) { innerTextField ->
                        Box(modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp)) {
                            if (searchText.isEmpty()) {
                                Text(
                                    text = stringResource(R.string.search_chat_context),
                                    color = BaekyoungTheme.colors.grayAC,
                                    style = BaekyoungTheme.typography.contentRegular,
                                    modifier = Modifier.align(Alignment.CenterStart),
                                )
                            }

                            Box(modifier = Modifier.fillMaxSize()) {
                                innerTextField()

                                if (searchText.isNotEmpty()) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_search_close),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .align(Alignment.CenterEnd)
                                            .padding(start = 10.dp)
                                            .clickable(
                                                interactionSource = remember { MutableInteractionSource() },
                                                indication = null,
                                            ) { clearSearchText() },
                                    )
                                }
                            }
                        }
                    }
                } else {
                    Text(
                        text = stringResource(id = titleTextId),
                        style = BaekyoungTheme.typography.contentBold,
                        color = textColor,
                        modifier = Modifier.align(Alignment.Center),
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(horizontal = 20.dp),
                    ) {
                        if (showSearchButton) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_search),
                                contentDescription = null,
                                modifier = Modifier.clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                ) { onShowSearchBarChanged() },
                            )
                        }

                        if (showDrawerButton) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_drawer),
                                contentDescription = null,
                                modifier = Modifier.clickable { onClickDrawerButton() },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewBaekyoungCenterTopBar() {
    BaekyoungTheme {
        Surface(color = BaekyoungTheme.colors.blueFF) {
            BaekyoungCenterTopBar(
                titleTextId = R.string.chatting,
                textColor = BaekyoungTheme.colors.white,
                showBackButton = true,
                showDrawerButton = true,
                showSearchButton = true,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewBaekyoungCenterTopBarWithoutBackButton() {
    BaekyoungTheme {
        Surface(color = BaekyoungTheme.colors.blueFF) {
            BaekyoungCenterTopBar(
                titleTextId = R.string.chatting,
                textColor = BaekyoungTheme.colors.white,
                showDrawerButton = true,
                showSearchButton = true,
                showBackButton = false,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewBaekyoungCenterTopBarWithoutDrawerButton() {
    BaekyoungTheme {
        Surface(color = BaekyoungTheme.colors.blueFF) {
            BaekyoungCenterTopBar(
                titleTextId = R.string.chatting,
                textColor = BaekyoungTheme.colors.white,
                showSearchButton = true,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewBaekyoungCenterTopBarWithoutRightButton() {
    BaekyoungTheme {
        Surface(color = BaekyoungTheme.colors.blueFF) {
            BaekyoungCenterTopBar(
                titleTextId = R.string.chatting,
                textColor = BaekyoungTheme.colors.white,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewBaekyoungCenterTopBarWithoutButton() {
    BaekyoungTheme {
        Surface(color = BaekyoungTheme.colors.blueFF) {
            BaekyoungCenterTopBar(
                titleTextId = R.string.chatting,
                textColor = BaekyoungTheme.colors.white,
                showBackButton = false,
            )
        }
    }
}
