package com.tgyuu.baekyoung_i.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tgyuu.baekyoung_i.home.HomeScreen

const val authHomeRoute = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions?) {
    this.navigate(authHomeRoute, navOptions)
}

fun NavGraphBuilder.homeScreen() {
    composable(route = authHomeRoute) {
        HomeRoute()
    }
}

@Composable
internal fun HomeRoute() {
    HomeScreen()
}