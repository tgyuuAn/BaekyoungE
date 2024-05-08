package com.tgyuu.feature.mentorchatting.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.tgyuu.feature.mentorchatting.MentorChattingRoute

const val mentorChattingNavigationRoute = "mentor_chatting_route"

fun NavController.navigateToMentorChatting(navOptions: NavOptions? = navOptions {}) {
    this.navigate(mentorChattingNavigationRoute, navOptions)
}

fun NavGraphBuilder.mentorChattingScreen(popBackStack: () -> Unit) {
    composable(route = mentorChattingNavigationRoute) {
        MentorChattingRoute(popBackStack = popBackStack)
    }
}
