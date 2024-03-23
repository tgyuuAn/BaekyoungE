package com.tgyuu.baekyounge.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.tgyuu.baekyounge.home.HomeRoute

const val homeNavigationRoute = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = navOptions {}) {
    this.navigate(homeNavigationRoute, navOptions)
}

fun NavGraphBuilder.homeScreen() {
    composable(route = homeNavigationRoute) {
        HomeRoute()
    }
}
