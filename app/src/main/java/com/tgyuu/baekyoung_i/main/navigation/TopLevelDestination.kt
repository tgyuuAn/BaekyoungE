package com.tgyuu.baekyoung_i.main.navigation

import android.graphics.drawable.Icon
import androidx.annotation.StringRes

enum class TopLevelDestination(
    val route: String,
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
) {
}