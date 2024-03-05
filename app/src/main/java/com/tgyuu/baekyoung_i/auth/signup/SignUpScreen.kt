package com.tgyuu.baekyoung_i.auth.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun SignUpRoute(navigateToHome: () -> Unit) {
    SignUpScreen(
        navigateToHome = navigateToHome,
    )
}

@Composable
internal fun SignUpScreen(navigateToHome: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(BaekyoungTheme.colors.blueF5FF)) {

    }
}