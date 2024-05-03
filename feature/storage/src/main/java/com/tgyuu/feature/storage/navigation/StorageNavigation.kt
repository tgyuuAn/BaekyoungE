package com.tgyuu.feature.storage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.tgyuu.feature.storage.StorageRoute

const val storageNavigationRoute = "storage_route"

fun NavController.navigateToStorage(navOptions: NavOptions? = navOptions {}) {
    this.navigate(storageNavigationRoute, navOptions)
}

fun NavGraphBuilder.storageScreen(navigateToChatting: (String) -> Unit) {
    composable(route = storageNavigationRoute) {
        StorageRoute(navigateToChatting)
    }
}
