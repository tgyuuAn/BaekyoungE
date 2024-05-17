package com.tgyuu.feature.chatting.mentoring.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.tgyuu.feature.chatting.mentoring.MentoringChattingRoute

fun mentorChattingNavigationRoute(roomId: String = "{roomId}") = "mentoring_chatting_route/$roomId"

fun NavController.navigateToMentorChatting(
    roomId: String,
    navOptions: NavOptions? = navOptions {},
) {
    this.navigate(mentorChattingNavigationRoute(roomId), navOptions)
}

fun NavGraphBuilder.mentorChattingScreen(popBackStack: () -> Unit) {
    composable(
        route = mentorChattingNavigationRoute(),
        arguments = listOf(navArgument("roomId") { type = NavType.StringType }),
    ) { navBackStackEntry ->
        val roomId = navBackStackEntry.arguments?.getString("roomId") ?: ""

        MentoringChattingRoute(
            roomId = roomId,
            popBackStack = popBackStack,
        )
    }
}
