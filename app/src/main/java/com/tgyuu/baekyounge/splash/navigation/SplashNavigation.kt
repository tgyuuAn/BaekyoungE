package com.tgyuu.baekyounge.splash.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.tgyuu.baekyounge.splash.SplashRoute

const val splashNavigationRoute = "splash_route"

fun NavController.navigateToSplash(navOptions: NavOptions? = navOptions {}) {
    this.navigate(splashNavigationRoute, navOptions)
}

fun NavGraphBuilder.splashScreen() {
    composable(route = splashNavigationRoute) {
        SplashRoute()
    }
}