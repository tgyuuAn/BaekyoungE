package com.tgyuu.baekyoung_i.consulting.consultinginformation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.tgyuu.baekyoung_i.consulting.consultinginformation.ConsultingRoute
import com.tgyuu.baekyoung_i.consulting.chatting.navigation.chattingScreen

const val consultingNavigationRoute = "consulting_route"

fun NavController.navigateToConsulting(navOptions: NavOptions? = navOptions {}) {
    this.navigate(consultingNavigationRoute, navOptions)
}

fun NavGraphBuilder.consultingGraph(
    navigateToChatting: () -> Unit,
) {
    composable(route = consultingNavigationRoute) {
        ConsultingRoute(navigateToChatting = navigateToChatting)
    }
    chattingScreen()
}