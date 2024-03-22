package com.tgyuu.baekyoung_i.storage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.tgyuu.baekyoung_i.shop.ShopRoute
import com.tgyuu.baekyoung_i.shop.navigation.shopNavigationRoute
import com.tgyuu.baekyoung_i.storage.StorageRoute

const val storageNavigationRoute = "storage_route"

fun NavController.navigateToStorage(navOptions: NavOptions? = navOptions {}) {
    this.navigate(storageNavigationRoute, navOptions)
}

fun NavGraphBuilder.storageScreen() {
    composable(route = storageNavigationRoute) {
        StorageRoute()
    }
}
