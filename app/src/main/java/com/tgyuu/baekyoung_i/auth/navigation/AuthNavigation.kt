package com.tgyuu.baekyoung_i.auth.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.tgyuu.baekyoung_i.auth.AuthRoute
import com.tgyuu.baekyoung_i.auth.AuthScreen

const val authNavigationRoute = "auth_route"

fun NavController.navigateToAuth(navOptions: NavOptions? = navOptions {}) {
    this.navigate(authNavigationRoute, navOptions)
}

fun NavGraphBuilder.authScreen(navigateToHome: () -> Unit) {
    composable(route = authNavigationRoute) {
        AuthRoute(navigateToHome)
    }
}