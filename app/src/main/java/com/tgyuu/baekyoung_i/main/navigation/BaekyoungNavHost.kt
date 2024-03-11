package com.tgyuu.baekyoung_i.main.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.tgyuu.baekyoung_i.auth.navigation.authNavigationRoute
import com.tgyuu.baekyoung_i.auth.navigation.authScreen
import com.tgyuu.baekyoung_i.auth.signup.navigation.navigateToSignUp
import com.tgyuu.baekyoung_i.auth.signup.navigation.signUpNavigationRoute
import com.tgyuu.baekyoung_i.auth.signup.navigation.signUpScreen
import com.tgyuu.baekyoung_i.community.navigation.communityScreen
import com.tgyuu.baekyoung_i.consulting.chatting.navigation.navigateToChatting
import com.tgyuu.baekyoung_i.consulting.consultinginformation.navigation.consultingGraph
import com.tgyuu.baekyoung_i.consulting.consultinginformation.navigation.consultingNavigationRoute
import com.tgyuu.baekyoung_i.etc.navigation.etcScreen
import com.tgyuu.baekyoung_i.home.navigation.homeScreen
import com.tgyuu.baekyoung_i.home.navigation.navigateToHome

@Composable
fun BaekyoungNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = authNavigationRoute,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            when (initialState.destination.route) {
                signUpNavigationRoute -> fadeIn(animationSpec = tween(700, 700))
                else -> fadeIn(animationSpec = tween(700))
            }
        },
        modifier = modifier,
    ) {
        authScreen(navigateToSignUp = {
            navController.navigateToSignUp(navOptions {
                popUpTo(authNavigationRoute) { inclusive = true }
            })
        })
        signUpScreen(navigateToHome = {
            navController.navigateToHome(navOptions {
                popUpTo(signUpNavigationRoute) { inclusive = true }
            })
        })
        homeScreen()
        consultingGraph(navigateToChatting = {
            navController.navigateToChatting(navOptions {
                popUpTo(consultingNavigationRoute)
            })
        })
        communityScreen()
        etcScreen()
    }
}