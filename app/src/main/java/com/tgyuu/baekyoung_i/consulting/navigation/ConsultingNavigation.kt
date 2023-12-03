package com.tgyuu.baekyoung_i.consulting.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.tgyuu.baekyoung_i.consulting.ConsultingScreen

const val consultingNavigationRoute = "consulting_route"

fun NavController.navigateToConsulting(navOptions: NavOptions? = navOptions {}) {
    this.navigate(consultingNavigationRoute, navOptions)
}

fun NavGraphBuilder.consultingScreen() {
    composable(route = consultingNavigationRoute) {
        ConsultingRoute()
    }
}

@Composable
internal fun ConsultingRoute() {
    ConsultingScreen()
}