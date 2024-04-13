package com.tgyuu.feature.auth.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.tgyuu.feature.auth.signup.SignUpRoute

fun signUpNavigationRoute(userId: String = "{userId}") = "sign_up_route/$userId"

fun NavController.navigateToSignUp(
    userId: String,
    navOptions: NavOptions? = navOptions {},
) {
    this.navigate(signUpNavigationRoute(userId), navOptions)
}

fun NavGraphBuilder.signUpScreen(navigateToHome: () -> Unit) {
    composable(
        route = signUpNavigationRoute("{userId}"),
        arguments = listOf(navArgument("userId") { type = NavType.StringType }),
    ) { navBackStackEntry ->
        val userId = navBackStackEntry.arguments?.getString("userId") ?: ""

        SignUpRoute(
            userId = userId,
            navigateToHome,
        )
    }
}
