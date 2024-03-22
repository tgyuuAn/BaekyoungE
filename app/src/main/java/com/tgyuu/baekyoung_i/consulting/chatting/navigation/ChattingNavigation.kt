package com.tgyuu.baekyoung_i.consulting.chatting.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.tgyuu.baekyoung_i.consulting.chatting.ChattingRoute

const val chattingNavigationRoute = "chatting_route"

fun NavController.navigateToChatting(navOptions: NavOptions? = navOptions {}) {
    this.navigate(chattingNavigationRoute, navOptions)
}

fun NavGraphBuilder.chattingScreen(popBackStack: () -> Unit) {
    composable(route = chattingNavigationRoute) {
        ChattingRoute(popBackStack = popBackStack)
    }
}