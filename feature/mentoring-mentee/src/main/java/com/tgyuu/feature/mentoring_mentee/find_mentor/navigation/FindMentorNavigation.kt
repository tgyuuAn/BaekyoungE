package com.tgyuu.feature.mentoring_mentee.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.tgyuu.feature.mentoring_mentee.find_mentor.FindMentorRoute

const val findMentorNavigationRoute = "find_mentor_route"

fun NavController.navigateToFindMentor(navOptions: NavOptions? = navOptions {}) {
    this.navigate(findMentorNavigationRoute, navOptions)
}

fun NavGraphBuilder.findMentorScreen(
    popBackStack: () -> Unit,
    navigateToMentorChatting: () -> Unit,
) {
    composable(route = findMentorNavigationRoute) {
        FindMentorRoute(
            popBackStack = popBackStack,
            navigateToMentorChatting = navigateToMentorChatting,
        )
    }
}
