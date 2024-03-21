package com.tgyuu.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {
        if (showBackButton) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(20.dp)
                    .clickable { onClickBackButton() }
            )
        }

        Text(
            text = stringResource(id = titleTextId),
            style = BaekyoungTheme.typography.contentBold,
            color = textColor,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 20.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(horizontal = 20.dp)
        ) {
            if (showSearchButton) {
                Image(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .clickable { onClickBackButton() }
                )
            }

            if (showDrawerButton) {
                Image(
                    painter = painterResource(id = R.drawable.ic_drawer),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .clickable { onClickDrawerButton() }
                )
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