package com.tgyuu.baekyoung_i.consulting.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
fun ConsultingTextField(
    title: String,
    value: String,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = BaekyoungTheme.typography.contentNormal.copy(fontSize = 18.sp),
            color = BaekyoungTheme.colors.black,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChanged,
            singleLine = true,
            keyboardOptions = keyboardOptions,
            textStyle = TextStyle.Default.copy(fontSize = 18.sp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Divider(
            color = BaekyoungTheme.colors.blue00,
            thickness = 1.dp
        )
    }
}