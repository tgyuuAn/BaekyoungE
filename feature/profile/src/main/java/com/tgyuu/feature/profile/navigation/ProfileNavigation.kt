package com.tgyuu.feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.tgyuu.feature.profile.ProfileRoute

const val profileNavigationRoute = "profile_route"

fun NavController.navigateToProfile(navOptions: NavOptions? = navOptions {}) {
    this.navigate(profileNavigationRoute, navOptions)
}

fun NavGraphBuilder.etcScreen() {
    composable(route = profileNavigationRoute) {
        ProfileRoute()
    }
}
