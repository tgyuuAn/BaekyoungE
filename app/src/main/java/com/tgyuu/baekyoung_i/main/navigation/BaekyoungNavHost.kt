package com.tgyuu.baekyoung_i.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.tgyuu.baekyoung_i.auth.navigation.authNavigationRoute
import com.tgyuu.baekyoung_i.auth.navigation.authScreen
import com.tgyuu.baekyoung_i.community.navigation.communityScreen
import com.tgyuu.baekyoung_i.consulting.navigation.consultingScreen
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
        modifier = modifier,
    ) {
        authScreen(navigateToHome = { navController.navigateToHome() })
        homeScreen()
        consultingScreen()
        communityScreen()
        etcScreen()
    }
}