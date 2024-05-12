package com.tgyuu.baekyounge.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tgyuu.chatting.ai.navigation.aiChattingNavigationRoute
import com.tgyuu.baekyounge.main.navigation.BaekyoungNavHost
import com.tgyuu.baekyounge.main.navigation.TopLevelDestination
import com.tgyuu.designsystem.theme.BaekyoungTheme
import com.tgyuu.feature.auth.navigation.authNavigationRoute
import com.tgyuu.feature.auth.signup.navigation.signUpNavigationRoute
import com.tgyuu.feature.chatting.mentoring.navigation.mentorChattingNavigationRoute
import com.tgyuu.feature.mentee.navigation.findMentorNavigationRoute
import com.tgyuu.feature.mentoring.mentee.navigation.mentoringMenteeNavigationRoute
import com.tgyuu.feature.profile.setting.navigation.settingNavigationRoute
import com.tgyuu.feature.shop.navigation.shopNavigationRoute
import com.tgyuu.feature.splash.navigation.splashNavigationRoute
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
                            },
                        )

                        BaekyoungBottomBar(
                            currentRoute = currentRoute,
                            bottomBarState = bottomBarState,
                            onNavigateToDestination = { destination ->
                                navigateToTopLevelDestination(
                                    navController,
                                    destination,
                                )
                            },
                            modifier = Modifier.height(60.dp),
                        )
                    },
                    modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars),
                ) { innerPadding ->
                    BaekyoungNavHost(
                        navController = navController,
                        modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
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
    splashNavigationRoute -> setBottomBarState(false)
    authNavigationRoute -> setBottomBarState(false)
    aiChattingNavigationRoute() -> setBottomBarState(false)
    signUpNavigationRoute() -> setBottomBarState(false)
    settingNavigationRoute -> setBottomBarState(false)
    mentoringMenteeNavigationRoute -> setBottomBarState(false)
    findMentorNavigationRoute -> setBottomBarState(false)
    mentorChattingNavigationRoute -> setBottomBarState(false)
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
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically(),
    ) {
        BottomNavigation(
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            modifier = modifier,
        ) {
            TopLevelDestination.entries.forEach { destination ->
                if ((currentRoute == com.tgyuu.feature.home.navigation.homeNavigationRoute) &&
                    (destination.route == com.tgyuu.feature.home.navigation.homeNavigationRoute)
                ) {
                    return@forEach
                }

                if ((currentRoute != com.tgyuu.feature.home.navigation.homeNavigationRoute) &&
                    (destination.route == shopNavigationRoute)
                ) {
                    return@forEach
                }

                val isSelect = currentRoute == destination.route
                val unselectedContentColor =
                    if (currentRoute == com.tgyuu.feature.home.navigation.homeNavigationRoute) {
                        BaekyoungTheme.colors.blueFB
                    } else {
                        BaekyoungTheme.colors.gray95
                    }

                BottomNavigationItem(
                    selected = isSelect,
                    modifier = Modifier.background(Color.Transparent),
                    onClick = { onNavigateToDestination(destination) },
                    selectedContentColor = BaekyoungTheme.colors.black,
                    unselectedContentColor = unselectedContentColor,
                    icon = {
                        Icon(
                            painter = painterResource(id = destination.selectedIcon),
                            contentDescription = null,
                        )
                    },
                )
            }
        }
    }
}

private fun ComponentActivity.setSystemBarTransParent() = enableEdgeToEdge()
