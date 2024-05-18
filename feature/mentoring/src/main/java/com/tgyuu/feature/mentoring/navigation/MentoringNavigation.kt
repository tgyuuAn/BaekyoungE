package com.tgyuu.feature.mentoring.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.tgyuu.feature.mentoring.MentoringRoute

const val mentoringNavigationRoute = "mentoring_route"

fun NavController.navigateToMentoring(navOptions: NavOptions? = navOptions {}) {
    this.navigate(mentoringNavigationRoute, navOptions)
}

fun NavGraphBuilder.mentoringScreen(
    navigateToMentoringMentor: () -> Unit,
    navigateToMentoringMentee: () -> Unit,
) {
    composable(route = mentoringNavigationRoute) {
        MentoringRoute(
            navigateToMentoringMentor = navigateToMentoringMentor,
            navigateToMentoringMentee = navigateToMentoringMentee,
        )
    }
}
