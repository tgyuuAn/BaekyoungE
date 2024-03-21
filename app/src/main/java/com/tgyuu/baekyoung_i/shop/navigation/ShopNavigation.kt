package com.tgyuu.baekyoung_i.shop.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.tgyuu.baekyoung_i.shop.ShopRoute

const val shopNavigationRoute = "shop_route"

fun NavController.navigateToShop(navOptions: NavOptions? = navOptions {}) {
    this.navigate(shopNavigationRoute, navOptions)
}

fun NavGraphBuilder.shopScreen() {
    composable(route = shopNavigationRoute) {
        ShopRoute()
    }
}
