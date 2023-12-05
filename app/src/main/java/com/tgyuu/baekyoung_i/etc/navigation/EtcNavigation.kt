package com.tgyuu.baekyoung_i.etc.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.tgyuu.baekyoung_i.etc.EtcRoute

const val etcNavigationRoute = "etc_route"

fun NavController.navigateToEtc(navOptions: NavOptions?  = navOptions {}) {
    this.navigate(etcNavigationRoute, navOptions)
}

fun NavGraphBuilder.etcScreen() {
    composable(route = etcNavigationRoute) {
        EtcRoute()
    }
}