package com.tgyuu.aichat.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.tgyuu.aichat.AiChattingRoute

fun aiChattingNavigationRoute(roomId: String = "{roomId}") = "ai_chatting_route/$roomId"

fun NavController.navigateToAiChatting(
    roomId: String,
    navOptions: NavOptions? = navOptions {},
) {
    this.navigate(aiChattingNavigationRoute(roomId), navOptions)
}

fun NavGraphBuilder.aiChattingScreen(popBackStack: () -> Unit) {
    composable(
        route = aiChattingNavigationRoute(),
        arguments = listOf(navArgument("roomId") { type = NavType.StringType }),
    ) { navBackStackEntry ->
        val roomId = navBackStackEntry.arguments?.getString("roomId") ?: ""
        AiChattingRoute(
            roomId = roomId,
            popBackStack = popBackStack,
        )
    }
}
