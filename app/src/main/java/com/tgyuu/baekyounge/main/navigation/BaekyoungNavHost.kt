package com.tgyuu.baekyounge.main.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.tgyuu.baekyounge.auth.navigation.authNavigationRoute
import com.tgyuu.baekyounge.auth.navigation.authScreen
import com.tgyuu.baekyounge.auth.navigation.navigateToAuth
import com.tgyuu.baekyounge.auth.signup.navigation.navigateToSignUp
import com.tgyuu.baekyounge.auth.signup.navigation.signUpNavigationRoute
import com.tgyuu.baekyounge.auth.signup.navigation.signUpScreen
import com.tgyuu.baekyounge.community.navigation.communityScreen
import com.tgyuu.baekyounge.consulting.chatting.navigation.navigateToChatting
import com.tgyuu.baekyounge.consulting.consultinginformation.navigation.consultingGraph
import com.tgyuu.baekyounge.consulting.consultinginformation.navigation.consultingNavigationRoute
import com.tgyuu.baekyounge.etc.navigation.etcScreen
import com.tgyuu.baekyounge.home.navigation.homeNavigationRoute
import com.tgyuu.baekyounge.home.navigation.homeScreen
import com.tgyuu.baekyounge.home.navigation.navigateToHome
import com.tgyuu.baekyounge.shop.navigation.shopScreen
import com.tgyuu.baekyounge.splash.navigation.splashNavigationRoute
import com.tgyuu.baekyounge.splash.navigation.splashScreen
import com.tgyuu.baekyounge.storage.navigation.storageScreen

@Composable
fun BaekyoungNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = splashNavigationRoute,
) {
    NavHost(
        navController = navController,
        enterTransition = {
            if (navController.currentDestination?.route == homeNavigationRoute &&
                navController.previousBackStackEntry?.destination?.route == signUpNavigationRoute
            ) {
                EnterTransition.None
            } else {
                fadeIn(animationSpec = tween(700))
            }
        },
        exitTransition = {
            if (navController.currentDestination?.route == signUpNavigationRoute) {
                ExitTransition.None
            } else {
                fadeOut(animationSpec = tween(700))
            }
        },
        startDestination = startDestination,
        modifier = modifier,
    ) {
        splashScreen(
            navigateToAuth = {
                navController.navigateToAuth(
                    navOptions {
                        popUpTo(splashNavigationRoute) { inclusive = true }
                    },
                )
            },
        )
        authScreen(
            navigateToSignUp = {
                navController.navigateToSignUp(
                    navOptions {
                        popUpTo(authNavigationRoute) { inclusive = true }
                    },
                )
            },
        )
        signUpScreen(
            navigateToHome = {
                navController.navigateToHome(
                    navOptions {
                        popUpTo(signUpNavigationRoute) { inclusive = true }
                    },
                )
            },
        )
        homeScreen()
        shopScreen()
        storageScreen()
        consultingGraph(
            navigateToChatting = {
                navController.navigateToChatting(
                    navOptions {
                        popUpTo(consultingNavigationRoute)
                    },
                )
            },
            popBackStack = { navController.popBackStack() },
        )
        communityScreen()
        etcScreen()
    }
}
