package com.tgyuu.baekyounge.shop.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.tgyuu.baekyounge.shop.ShopRoute

const val shopNavigationRoute = "shop_route"

fun NavController.navigateToShop(navOptions: NavOptions? = navOptions {}) {
    this.navigate(shopNavigationRoute, navOptions)
}

fun NavGraphBuilder.shopScreen() {
    composable(route = shopNavigationRoute) {
        ShopRoute()
    }
}
