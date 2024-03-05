package com.tgyuu.baekyoung_i.auth.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tgyuu.baekyoung_i.R
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
fun AuthButton(
    text: String,
    @DrawableRes image: Int,
    backgroundColor: Color,
    borderStroke: BorderStroke? = null,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        border = borderStroke,
    ) {

    }
}

@Preview
@Composable
fun PreviewAuthButton() {
    AuthButton(
        text = "이메일로 가입",
        image = R.drawable.ic_signup,
        backgroundColor = BaekyoungTheme.colors.blueF8,
        onClick = {}
    )
}