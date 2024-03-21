package com.tgyuu.baekyoung_i.main.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.tgyuu.baekyoung_i.R
import com.tgyuu.baekyoung_i.community.navigation.communityNavigationRoute
import com.tgyuu.baekyoung_i.consulting.consultinginformation.navigation.consultingNavigationRoute
import com.tgyuu.baekyoung_i.etc.navigation.profileNavigationRoute
import com.tgyuu.baekyoung_i.home.navigation.homeNavigationRoute

enum class TopLevelDestination(
    val route: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
) {
    CONSULTING(
        route = consultingNavigationRoute,
        selectedIcon = R.drawable.ic_selected_consulting,
        unselectedIcon = R.drawable.ic_consulting,
        iconTextId = R.string.consulting,
        titleTextId = R.string.consulting,
    ),
    STORAGE(
        route = consultingNavigationRoute,
        selectedIcon = R.drawable.ic_selected_storage,
        unselectedIcon = R.drawable.ic_storage,
        iconTextId = R.string.storage,
        titleTextId = R.string.storage,
    ),
    HOME(
        route = homeNavigationRoute,
        selectedIcon = R.drawable.ic_selected_home,
        unselectedIcon = R.drawable.ic_home,
        iconTextId = R.string.home,
        titleTextId = R.string.home,
    ),
    COMMUNITY(
        route = communityNavigationRoute,
        selectedIcon = R.drawable.ic_selected_community,
        unselectedIcon = R.drawable.ic_community,
        iconTextId = R.string.community,
        titleTextId = R.string.community,
    ),
    PROFILE(
        route = profileNavigationRoute,
        selectedIcon = R.drawable.ic_selected_profile,
        unselectedIcon = R.drawable.ic_profile,
        iconTextId = R.string.profile,
        titleTextId = R.string.profile,
    ),
}