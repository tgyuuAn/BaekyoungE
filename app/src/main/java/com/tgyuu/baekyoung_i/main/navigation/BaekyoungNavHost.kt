package com.tgyuu.baekyoung_i.main.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
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
import com.tgyuu.baekyoung_i.shop.navigation.shopScreen
import com.tgyuu.baekyoung_i.storage.navigation.storageScreen

@Composable
fun BaekyoungNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = authNavigationRoute,
) {
    NavHost(
        navController = navController,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        startDestination = startDestination,
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
        shopScreen()
        storageScreen()
        consultingGraph(navigateToChatting = {
            navController.navigateToChatting(navOptions {
                popUpTo(consultingNavigationRoute)
            })
        })
        communityScreen()
        etcScreen()
    }
}