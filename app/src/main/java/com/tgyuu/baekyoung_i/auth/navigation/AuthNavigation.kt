package com.tgyuu.baekyoung_i.auth.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val authNavigationRoute = "auth_route"

fun NavGraphBuilder.authScreen() {
    composable(route = authNavigationRoute) {
        AuthRoute()
    }
}

@Composable
internal fun AuthRoute() {
    AuthScreen()
}

@Composable
internal fun AuthScreen() {

}