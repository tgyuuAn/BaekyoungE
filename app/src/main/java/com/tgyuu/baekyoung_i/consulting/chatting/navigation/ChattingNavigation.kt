package com.tgyuu.baekyoung_i.consulting.chatting.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.tgyuu.baekyoung_i.consulting.ConsultingRoute
import com.tgyuu.baekyoung_i.consulting.chatting.ChattingRoute
import com.tgyuu.baekyoung_i.consulting.navigation.consultingNavigationRoute

const val chattingNavigationRoute = "chatting_route"

fun NavController.navigateToConsulting(navOptions: NavOptions? = navOptions {}) {
    this.navigate(chattingNavigationRoute, navOptions)
}

fun NavGraphBuilder.chattingScreen() {
    composable(route = chattingNavigationRoute) {
        ChattingRoute()
    }
}