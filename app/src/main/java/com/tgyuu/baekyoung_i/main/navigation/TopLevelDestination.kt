package com.tgyuu.baekyoung_i.main.navigation

import android.graphics.drawable.Icon
import androidx.annotation.StringRes
import androidx.compose.material.Icon
import androidx.compose.ui.res.painterResource
import com.tgyuu.baekyoung_i.R
import com.tgyuu.baekyoung_i.home.navigation.homeNavigationRoute

enum class TopLevelDestination(
    val route: String,
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
) {
    HOME(
        route = homeNavigationRoute,
        selectedIcon = Icon(
            painterResource(id = R.drawable.ic_selected_home),
            contentDescription = null,
        ),
        unselectedIcon = Icon()
}