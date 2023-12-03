package com.tgyuu.baekyoung_i.main.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.tgyuu.baekyoung_i.R
import com.tgyuu.baekyoung_i.community.navigation.communityNavigationRoute
import com.tgyuu.baekyoung_i.consulting.navigation.consultingNavigationRoute
import com.tgyuu.baekyoung_i.etc.navigation.etcNavigationRoute
import com.tgyuu.baekyoung_i.home.navigation.homeNavigationRoute

enum class TopLevelDestination(
    val route: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
) {
    HOME(
        route = homeNavigationRoute,
        selectedIcon = R.drawable.ic_selected_home,
        unselectedIcon = R.drawable.ic_home,
        iconTextId = R.string.home,
        titleTextId = R.string.home,
    ),
    CONSULTING(
        route = consultingNavigationRoute,
        selectedIcon = R.drawable.ic_selected_consulting,
        unselectedIcon = R.drawable.ic_consulting,
        iconTextId = R.string.consulting,
        titleTextId = R.string.consulting,
    ),
    COMMUNITY(
        route = communityNavigationRoute,
        selectedIcon = R.drawable.ic_selected_community,
        unselectedIcon = R.drawable.ic_community,
        iconTextId = R.string.community,
        titleTextId = R.string.community,
    ),
    ETC(
        route = etcNavigationRoute,
        selectedIcon = R.drawable.ic_selected_etc,
        unselectedIcon = R.drawable.ic_etc,
        iconTextId = R.string.etc,
        titleTextId = R.string.etc,
    ),
}