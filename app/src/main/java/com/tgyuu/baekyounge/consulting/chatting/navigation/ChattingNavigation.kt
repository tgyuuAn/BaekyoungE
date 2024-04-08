package com.tgyuu.baekyounge.consulting.chatting.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.tgyuu.baekyounge.consulting.chatting.ChattingRoute

fun chattingNavigationRoute(userId: String = "{userId}") = "chatting_route/$userId"

fun NavController.navigateToChatting(
    userId: String,
    navOptions: NavOptions? = navOptions {},
) {
    this.navigate(chattingNavigationRoute(userId), navOptions)
}

fun NavGraphBuilder.chattingScreen(popBackStack: () -> Unit) {
    composable(
        route = chattingNavigationRoute(),
        arguments = listOf(navArgument("userId") { type = NavType.StringType }),
    ) { navBackStackEntry ->
        val userId = navBackStackEntry.arguments?.getString("userId") ?: ""

        ChattingRoute(
            userId = userId,
            popBackStack = popBackStack,
        )
    }
}
