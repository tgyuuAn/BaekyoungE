package com.tgyuu.feature.profile.setting.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tgyuu.feature.profile.setting.SettingRoute

const val settingNavigationRoute = "setting_route"

fun NavController.navigateToSetting(navOptions: NavOptions? = androidx.navigation.navOptions {}) {
    this.navigate(settingNavigationRoute, navOptions)
}

fun NavGraphBuilder.settingScreen(
    popBackStack: () -> Unit,
    navigateToAuth: () -> Unit,
) {
    composable(route = settingNavigationRoute) {
        SettingRoute(popBackStack = popBackStack, navigateToAuth = navigateToAuth)
    }
}
