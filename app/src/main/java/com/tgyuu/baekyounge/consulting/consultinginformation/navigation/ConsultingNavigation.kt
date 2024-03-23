package com.tgyuu.baekyounge.consulting.consultinginformation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.tgyuu.baekyounge.consulting.chatting.navigation.chattingScreen
import com.tgyuu.baekyounge.consulting.consultinginformation.ConsultingRoute

const val consultingNavigationRoute = "consulting_route"

fun NavController.navigateToConsulting(navOptions: NavOptions? = navOptions {}) {
    this.navigate(consultingNavigationRoute, navOptions)
}

fun NavGraphBuilder.consultingGraph(
    navigateToChatting: () -> Unit,
    popBackStack: () -> Unit,
) {
    composable(route = consultingNavigationRoute) {
        ConsultingRoute(navigateToChatting = navigateToChatting)
    }
    chattingScreen(popBackStack = popBackStack)
}
