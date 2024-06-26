package com.tgyuu.feature.consulting.consultinginformation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.tgyuu.feature.consulting.ConsultingRoute

const val consultingNavigationRoute = "consulting_route"

fun NavController.navigateToConsulting(navOptions: NavOptions? = navOptions {}) {
    this.navigate(consultingNavigationRoute, navOptions)
}

fun NavGraphBuilder.consultingScreen(navigateToChatting: (String) -> Unit) {
    composable(route = consultingNavigationRoute) {
        ConsultingRoute(navigateToChatting = navigateToChatting)
    }
}
