package com.tgyuu.feature.etc.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.tgyuu.feature.etc.EtcRoute

const val profileNavigationRoute = "etc_route"

fun NavController.navigateToEtc(navOptions: NavOptions? = navOptions {}) {
    this.navigate(profileNavigationRoute, navOptions)
}

fun NavGraphBuilder.etcScreen() {
    composable(route = profileNavigationRoute) {
        EtcRoute()
    }
}
