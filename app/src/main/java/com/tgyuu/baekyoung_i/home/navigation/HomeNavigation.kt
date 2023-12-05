package com.tgyuu.baekyoung_i.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.tgyuu.baekyoung_i.home.HomeRoute
import com.tgyuu.baekyoung_i.home.HomeScreen

const val homeNavigationRoute = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = navOptions {}) {
    this.navigate(homeNavigationRoute, navOptions)
}

fun NavGraphBuilder.homeScreen() {
    composable(route = homeNavigationRoute) {
        HomeRoute()
    }
}