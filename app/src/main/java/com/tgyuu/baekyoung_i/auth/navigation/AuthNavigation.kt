package com.tgyuu.baekyoung_i.auth.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tgyuu.baekyoung_i.auth.AuthScreen

const val authNavigationRoute = "auth_route"

fun NavController.navigateToAuth(navOptions: NavOptions?){
    this.navigate(authNavigationRoute, navOptions)
}

fun NavGraphBuilder.authScreen() {
    composable(route = authNavigationRoute) {
        AuthRoute()
    }
}

@Composable
internal fun AuthRoute() {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val idChanged : (String) -> Unit = { textValue ->
        id = textValue
    }

    val passwordChanged : (String) -> Unit = { textValue ->
        password = textValue
    }
    AuthScreen(id, password, idChanged, passwordChanged)
}