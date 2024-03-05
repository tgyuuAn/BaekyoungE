package com.tgyuu.baekyoung_i.auth.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tgyuu.baekyoung_i.R
import com.tgyuu.baekyoung_i.auth.signup.component.SignUpTextField
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun SignUpRoute(navigateToHome: () -> Unit) {
    SignUpScreen(
        navigateToHome = navigateToHome,
    )
}

@Composable
internal fun SignUpScreen(navigateToHome: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BaekyoungTheme.colors.blueF5FF)
    ) {
        SignUpTextField(
            title = R.string.nickname,
            hint = R.string.nickname_hint,
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 60.dp),
        )
    }
}