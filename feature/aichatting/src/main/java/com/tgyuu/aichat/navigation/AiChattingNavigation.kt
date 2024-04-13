package com.tgyuu.aichat.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.tgyuu.aichat.AiChattingRoute

fun aiChattingNavigationRoute(userId: String = "{userId}") = "ai_chatting_route/$userId"

fun NavController.navigateToAiChatting(
    userId: String,
    navOptions: NavOptions? = navOptions {},
) {
    this.navigate(aiChattingNavigationRoute(userId), navOptions)
}

fun NavGraphBuilder.aiChattingScreen(popBackStack: () -> Unit) {
    composable(
        route = aiChattingNavigationRoute(),
        arguments = listOf(navArgument("userId") { type = NavType.StringType }),
    ) { navBackStackEntry ->
        val userId = navBackStackEntry.arguments?.getString("userId") ?: ""
        AiChattingRoute(
            userId = userId,
            popBackStack = popBackStack,
        )
    }
}
