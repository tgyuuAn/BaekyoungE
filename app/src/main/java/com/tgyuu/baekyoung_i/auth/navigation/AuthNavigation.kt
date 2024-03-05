package com.tgyuu.baekyoung_i.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.tgyuu.baekyoung_i.auth.AuthRoute

const val authNavigationRoute = "auth_route"

fun NavController.navigateToAuth(navOptions: NavOptions? = navOptions {}) {
    this.navigate(authNavigationRoute, navOptions)
}

fun NavGraphBuilder.authScreen(navigateToSignUp: () -> Unit) {
    composable(route = authNavigationRoute) {
        AuthRoute(navigateToSignUp)
    }
}