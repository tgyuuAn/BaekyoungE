package com.tgyuu.feature.mentoring.mentee.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.tgyuu.feature.mentoring.mentee.MentoringMenteeRoute

const val mentoringMenteeNavigationRoute = "mentoring_mentee_route"

fun NavController.navigateToMentoringMentee(navOptions: NavOptions? = navOptions {}) {
    this.navigate(mentoringMenteeNavigationRoute, navOptions)
}

fun NavGraphBuilder.mentoringMenteeScreen(
    navigateToFindMentor: () -> Unit,
    navigateToMentoringChatting: (String, String) -> Unit,
    popBackStack: () -> Unit,
) {
    composable(route = mentoringMenteeNavigationRoute) {
        MentoringMenteeRoute(
            navigateToFindMentor = navigateToFindMentor,
            navigateToMentoringChatting = navigateToMentoringChatting,
            popBackStack = popBackStack,
        )
    }
}
