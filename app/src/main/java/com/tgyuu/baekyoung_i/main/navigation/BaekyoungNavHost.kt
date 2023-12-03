package com.tgyuu.baekyoung_i.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.tgyuu.baekyoung_i.auth.navigation.authNavigationRoute
import com.tgyuu.baekyoung_i.auth.navigation.authScreen

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
        authScreen()
    }
}