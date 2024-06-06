package com.tgyuu.feature.mentee.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.tgyuu.feature.mentoring.mentee.findmentor.FindMentorRoute

const val findMentorNavigationRoute = "find_mentor_route"

fun NavController.navigateToFindMentor(navOptions: NavOptions? = navOptions {}) {
    this.navigate(findMentorNavigationRoute, navOptions)
}

fun NavGraphBuilder.findMentorScreen(
    popBackStack: () -> Unit,
    navigateToMentoringChatting: (String, String) -> Unit,
) {
    composable(route = findMentorNavigationRoute) {
        FindMentorRoute(
            popBackStack = popBackStack,
            navigateToMentoringChatting = navigateToMentoringChatting,
        )
    }
}
