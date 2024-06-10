package com.tgyuu.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
fun BaekyoungDialog(
    title: String,
    description: String = "",
    descriptionColor: Color = BaekyoungTheme.colors.red,
    leftButtonText: String,
    leftButtonTextColor: Color = BaekyoungTheme.colors.black,
    leftButtonColor: Color,
    rightButtonText: String,
    rightButtonTextColor: Color = BaekyoungTheme.colors.white,
    rightButtonColor: Color,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    onLeftButtonClick: () -> Unit,
    onRightButtonClick: () -> Unit,
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(shape = RoundedCornerShape(10.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .background(BaekyoungTheme.colors.white)
                    .padding(vertical = 16.dp, horizontal = 20.dp),
            ) {
                Text(
                    text = title,
                    style = BaekyoungTheme.typography.labelBold.copy(fontSize = 14.sp),
                    modifier = Modifier.padding(bottom = 2.dp),
                )

                if (description.isNotBlank()) {
                    Text(
                        text = description,
                        style = BaekyoungTheme.typography.labelRegular.copy(fontSize = 10.sp),
                        color = descriptionColor,
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                ) {
                    BaekyoungButton(
                        text = leftButtonText,
                        textColor = leftButtonTextColor,
                        buttonColor = leftButtonColor,
                        onButtonClick = onLeftButtonClick,
                        modifier = Modifier.weight(1f),
                    )

                    BaekyoungButton(
                        text = rightButtonText,
                        textColor = rightButtonTextColor,
                        buttonColor = rightButtonColor,
                        onButtonClick = onRightButtonClick,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}
