package com.tgyuu.feature.chatting.mentoring.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.tgyuu.feature.chatting.mentoring.MentoringChattingRoute

fun mentorChattingNavigationRoute(
    userId: String = "{userId}",
    roomId: String = "{roomId}",
) = "mentoring_chatting_route/$userId/$roomId"

fun NavController.navigateToMentoringChatting(
    userId: String,
    roomId: String,
    navOptions: NavOptions? = navOptions {},
) {
    this.navigate(mentorChattingNavigationRoute(userId, roomId), navOptions)
}

fun NavGraphBuilder.mentoringChattingScreen(popBackStack: () -> Unit) {
    composable(
        route = mentorChattingNavigationRoute(),
        arguments = listOf(
            navArgument("roomId") { type = NavType.StringType },
            navArgument("userId") { type = NavType.StringType }
        ),
    ) { navBackStackEntry ->
        val roomId = navBackStackEntry.arguments?.getString("roomId") ?: ""
        val userId = navBackStackEntry.arguments?.getString("userId") ?: ""

        MentoringChattingRoute(
            userId = userId,
            roomId = roomId,
            popBackStack = popBackStack,
        )
    }
}
