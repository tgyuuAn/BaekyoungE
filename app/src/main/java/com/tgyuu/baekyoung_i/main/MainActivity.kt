package com.tgyuu.baekyoung_i.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tgyuu.baekyoung_i.auth.navigation.authNavigationRoute
import com.tgyuu.baekyoung_i.auth.signup.navigation.signUpNavigationRoute
import com.tgyuu.baekyoung_i.consulting.chatting.navigation.chattingNavigationRoute
import com.tgyuu.baekyoung_i.main.navigation.BaekyoungNavHost
import com.tgyuu.baekyoung_i.main.navigation.TopLevelDestination
import com.tgyuu.designsystem.theme.BaekyoungTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSystemBarTransParent()

        setContent {
            BaekyoungTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentRoute = navBackStackEntry?.destination?.route
                        var bottomBarState by rememberSaveable { mutableStateOf(false) }

                        handleBottomBarState(
                            currentRoute,
                            setBottomBarState = { boolean ->
                                bottomBarState = boolean
                            }
                        )

                        BaekyoungBottomBar(
                            currentRoute = currentRoute,
                            bottomBarState = bottomBarState,
                            onNavigateToDestination = { destination ->
                                navigateToTopLevelDestination(
                                    navController,
                                    destination
                                )
                            },
                            modifier = Modifier.height(70.dp)
                        )
                    },
                    modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
                ) { innerPadding ->
                    BaekyoungNavHost(
                        navController = navController,
                        modifier = Modifier.padding(top = innerPadding.calculateTopPadding())
                    )
                }
            }
        }
    }
}

private fun handleBottomBarState(
    currentRoute: String?,
    setBottomBarState: (Boolean) -> Unit,
): Unit = when (currentRoute) {
    null -> setBottomBarState(false)
    authNavigationRoute -> setBottomBarState(false)
    chattingNavigationRoute -> setBottomBarState(false)
    signUpNavigationRoute -> setBottomBarState(false)
    else -> setBottomBarState(true)
}

private fun navigateToTopLevelDestination(
    navController: NavController,
    destination: TopLevelDestination,
) {
    navController.navigate(route = destination.route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
internal fun BaekyoungBottomBar(
    modifier: Modifier = Modifier,
    currentRoute: String?,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    bottomBarState: Boolean,
) {
    AnimatedVisibility(
        visible = bottomBarState,
        label = "",
    ) {
        BottomNavigation(
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            modifier = modifier,
        ) {
            TopLevelDestination.entries.forEach { destination ->
                val isSelect = currentRoute == destination.route
                BottomNavigationItem(
                    selected = isSelect,
                    modifier = Modifier.background(Color.Transparent),
                    onClick = { onNavigateToDestination(destination) },
                    selectedContentColor = BaekyoungTheme.colors.blueFF,
                    unselectedContentColor = BaekyoungTheme.colors.gray95,
                    icon = {
                        Icon(
                            painter = painterResource(id = destination.selectedIcon),
                            contentDescription = null,
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(id = destination.titleTextId),
                            style = BaekyoungTheme.typography.labelNormal,
                            color = if (isSelect) BaekyoungTheme.colors.blueFF
                            else BaekyoungTheme.colors.gray95,
                        )
                    }
                )
            }
        }
    }
}

private fun ComponentActivity.setSystemBarTransParent() = enableEdgeToEdge()