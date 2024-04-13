package com.tgyuu.baekyounge.main.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.tgyuu.baekyounge.R
import com.tgyuu.baekyounge.community.navigation.communityNavigationRoute
import com.tgyuu.feature.consulting.consultinginformation.navigation.consultingNavigationRoute
import com.tgyuu.feature.etc.navigation.profileNavigationRoute
import com.tgyuu.baekyounge.shop.navigation.shopNavigationRoute
import com.tgyuu.baekyounge.storage.navigation.storageNavigationRoute

enum class TopLevelDestination(
    val route: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    @DrawableRes val homeIcon: Int,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
) {
    CONSULTING(
        route = consultingNavigationRoute,
        homeIcon = R.drawable.ic_home_consulting,
        selectedIcon = R.drawable.ic_selected_consulting,
        unselectedIcon = R.drawable.ic_consulting,
        iconTextId = R.string.consulting,
        titleTextId = R.string.consulting,
    ),
    STORAGE(
        route = storageNavigationRoute,
        homeIcon = R.drawable.ic_home_storage,
        selectedIcon = R.drawable.ic_selected_storage,
        unselectedIcon = R.drawable.ic_storage,
        iconTextId = R.string.storage,
        titleTextId = R.string.storage,
    ),
    SHOP(
        route = shopNavigationRoute,
        homeIcon = R.drawable.ic_selected_shop,
        selectedIcon = R.drawable.ic_selected_shop,
        unselectedIcon = R.drawable.ic_selected_shop,
        iconTextId = R.string.shop,
        titleTextId = R.string.shop,
    ),
    HOME(
        route = com.tgyuu.feature.home.navigation.homeNavigationRoute,
        homeIcon = R.drawable.ic_home_home,
        selectedIcon = R.drawable.ic_selected_home,
        unselectedIcon = R.drawable.ic_home,
        iconTextId = R.string.home,
        titleTextId = R.string.home,
    ),
    COMMUNITY(
        route = communityNavigationRoute,
        homeIcon = R.drawable.ic_home_community,
        selectedIcon = R.drawable.ic_selected_community,
        unselectedIcon = R.drawable.ic_community,
        iconTextId = R.string.community,
        titleTextId = R.string.community,
    ),
    PROFILE(
        route = profileNavigationRoute,
        homeIcon = R.drawable.ic_home_profile,
        selectedIcon = R.drawable.ic_selected_profile,
        unselectedIcon = R.drawable.ic_profile,
        iconTextId = R.string.profile,
        titleTextId = R.string.profile,
    ),
}
