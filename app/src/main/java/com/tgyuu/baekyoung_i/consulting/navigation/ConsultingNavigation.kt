package com.tgyuu.baekyoung_i.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tgyuu.baekyoung_i.home.ConsultingScreen

const val authConsultingRoute = "consulting_route"

fun NavController.navigateToConsulting(navOptions: NavOptions?) {
    this.navigate(authConsultingRoute, navOptions)
}

fun NavGraphBuilder.consultingScreen() {
    composable(route = authConsultingRoute) {
        ConsultingRoute()
    }
}

@Composable
internal fun ConsultingRoute() {
    ConsultingScreen()
}