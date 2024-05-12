package com.tgyuu.feature.mentoring.mentor.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.tgyuu.feature.mentoring.mentor.MentoringMentorRoute

const val mentoringMentorNavigationRoute = "mentoring_mentor_route"

fun NavController.navigateToMentoringMentor(navOptions: NavOptions? = navOptions {}) {
    this.navigate(mentoringMentorNavigationRoute, navOptions)
}

fun NavGraphBuilder.mentoringMentorScreen(popBackStack: () -> Unit) {
    composable(route = mentoringMentorNavigationRoute) {
        MentoringMentorRoute(
            popBackStack = popBackStack,
        )
    }
}
