package com.tgyuu.feature.community.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.tgyuu.feature.community.CommunityRoute

const val communityNavigationRoute = "community_route"

fun NavController.navigateToCommunity(navOptions: NavOptions? = navOptions {}) {
    this.navigate(communityNavigationRoute, navOptions)
}

fun NavGraphBuilder.communityScreen() {
    composable(route = communityNavigationRoute) {
        CommunityRoute()
    }
}
