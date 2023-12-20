package com.tgyuu.baekyoung_i.consulting.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
fun ConsultingTextField(
    title: String,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = BaekyoungTheme.typography.contentNormal,
            modifier = Modifier.align(Alignment.CenterStart)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChanged,
            singleLine = true,
            textStyle = TextStyle.Default.copy(fontSize = 16.sp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BaekyoungTheme.colors.blue37,
                unfocusedBorderColor = BaekyoungTheme.colors.blue37,
            ),
            modifier = Modifier
                .size(height = 30.dp, width = 200.dp)
                .align(Alignment.CenterEnd),
        )
    }
}