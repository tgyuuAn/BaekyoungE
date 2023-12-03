package com.tgyuu.baekyoung_i.community.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tgyuu.baekyoung_i.community.CommunityScreen
import com.tgyuu.baekyoung_i.consulting.navigation.consultingNavigationRoute

const val communityNavigationRoute = "community_route"

fun NavController.navigateToCommunity(navOptions: NavOptions?) {
    this.navigate(communityNavigationRoute, navOptions)
}

fun NavGraphBuilder.communityScreen() {
    composable(route = consultingNavigationRoute) {
        CommunityRoute()
    }
}

@Composable
internal fun CommunityRoute() {
    CommunityScreen()
}