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
import com.tgyuu.aichat.navigation.aiChattingScreen
import com.tgyuu.aichat.navigation.navigateToAiChatting
import com.tgyuu.feature.auth.navigation.authNavigationRoute
import com.tgyuu.feature.auth.navigation.authScreen
import com.tgyuu.feature.auth.navigation.navigateToAuth
import com.tgyuu.feature.auth.signup.navigation.navigateToSignUp
import com.tgyuu.feature.auth.signup.navigation.signUpNavigationRoute
import com.tgyuu.feature.auth.signup.navigation.signUpScreen
import com.tgyuu.feature.consulting.consultinginformation.navigation.consultingNavigationRoute
import com.tgyuu.feature.consulting.consultinginformation.navigation.consultingScreen
import com.tgyuu.feature.home.navigation.homeScreen
import com.tgyuu.feature.home.navigation.navigateToHome
import com.tgyuu.feature.mentoring.navigation.mentoringNavigationRoute
import com.tgyuu.feature.mentoring.navigation.mentoringScreen
import com.tgyuu.feature.mentoring_mentee.navigation.findMentorScreen
import com.tgyuu.feature.mentoring_mentee.navigation.mentoringMenteeScreen
import com.tgyuu.feature.mentoring_mentee.navigation.navigateToFindMentor
import com.tgyuu.feature.mentoring_mentee.navigation.navigateToMentoringMentee
import com.tgyuu.feature.profile.navigation.profileScreen
import com.tgyuu.feature.profile.setting.navigation.navigateToSetting
import com.tgyuu.feature.profile.setting.navigation.settingNavigationRoute
import com.tgyuu.feature.profile.setting.navigation.settingScreen
import com.tgyuu.feature.shop.navigation.shopScreen
import com.tgyuu.feature.splash.navigation.splashNavigationRoute
import com.tgyuu.feature.splash.navigation.splashScreen
import com.tgyuu.feature.storage.navigation.storageNavigationRoute
import com.tgyuu.feature.storage.navigation.storageScreen

@Composable
fun BaekyoungNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = splashNavigationRoute,
) {
    NavHost(
        navController = navController,
        enterTransition = {
            if (navController.currentDestination?.route == com.tgyuu.feature.home.navigation.homeNavigationRoute &&
                navController.previousBackStackEntry?.destination?.route == signUpNavigationRoute()
            ) {
                EnterTransition.None
            } else {
                fadeIn(animationSpec = tween(700))
            }
        },
        exitTransition = {
            if (navController.currentDestination?.route == signUpNavigationRoute()) {
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
            navigateToHome = {
                navController.navigateToHome(
                    navOptions {
                        popUpTo(splashNavigationRoute) { inclusive = true }
                    },
                )
            },
        )
        authScreen(
            navigateToSignUp = {
                navController.navigateToSignUp(
                    userId = it,
                    navOptions {
                        popUpTo(authNavigationRoute) { inclusive = true }
                    },
                )
            },
            navigateToHome = {
                navController.navigateToHome(
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
                        popUpTo(signUpNavigationRoute()) { inclusive = true }
                    },
                )
            },
        )
        homeScreen()
        shopScreen()
        storageScreen(
            navigateToChatting = {
                navController.navigateToAiChatting(
                    roomId = it,
                    navOptions {
                        popUpTo(storageNavigationRoute)
                    },
                )
            },
        )
        consultingScreen(
            navigateToChatting = {
                navController.navigateToAiChatting(
                    roomId = it,
                    navOptions {
                        popUpTo(consultingNavigationRoute)
                    },
                )
            },
        )
        aiChattingScreen(popBackStack = { navController.popBackStack() })
        mentoringScreen(
            navigateToMentoringMentee = {
                navController.navigateToMentoringMentee(
                    navOptions { popUpTo(mentoringNavigationRoute) },
                )
            },
        )
        mentoringMenteeScreen(
            navigateToFindMentor = {
                navController.navigateToFindMentor(
                    navOptions { popUpTo(mentoringNavigationRoute) },
                )
            },
            popBackStack = { navController.popBackStack() },
        )
        findMentorScreen(popBackStack = { navController.popBackStack() })
        profileScreen(navigateToSetting = navController::navigateToSetting)
        settingScreen(
            popBackStack = { navController.popBackStack() },
            navigateToAuth = {
                navController.navigateToAuth(
                    navOptions {
                        popUpTo(settingNavigationRoute) { inclusive = true }
                    },
                )
            },
        )
    }
}
