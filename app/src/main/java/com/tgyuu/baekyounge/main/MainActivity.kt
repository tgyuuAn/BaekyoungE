package com.tgyuu.baekyounge.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.ACTION_APP_NOTIFICATION_SETTINGS
import android.provider.Settings.ACTION_WIFI_SETTINGS
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.FirebaseAnalytics.Event.SCREEN_VIEW
import com.google.firebase.analytics.FirebaseAnalytics.Param.SCREEN_NAME
import com.tgyuu.baekyounge.R.string
import com.tgyuu.baekyounge.main.navigation.BaekyoungNavHost
import com.tgyuu.baekyounge.main.navigation.TopLevelDestination
import com.tgyuu.chatting.ai.navigation.aiChattingNavigationRoute
import com.tgyuu.designsystem.component.BaekyoungDialog
import com.tgyuu.designsystem.theme.BaekyoungTheme
import com.tgyuu.feature.auth.navigation.authNavigationRoute
import com.tgyuu.feature.auth.signup.navigation.signUpNavigationRoute
import com.tgyuu.feature.chatting.mentoring.navigation.mentorChattingNavigationRoute
import com.tgyuu.feature.mentee.navigation.findMentorNavigationRoute
import com.tgyuu.feature.mentoring.mentee.navigation.mentoringMenteeNavigationRoute
import com.tgyuu.feature.mentoring.mentor.navigation.mentoringMentorNavigationRoute
import com.tgyuu.feature.profile.setting.navigation.settingNavigationRoute
import com.tgyuu.feature.splash.navigation.splashNavigationRoute
import com.tgyuu.feature.storage.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var firebaseAnalytics: FirebaseAnalytics

    @Inject
    lateinit var networkObserver: NetworkObserver

    private var showPermissionDeniedSnackbar by mutableStateOf(false)

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (!isGranted) {
            showPermissionDeniedSnackbar = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        askNotificationPermission()

        setContent {
            val navController = rememberNavController()
            val snackbarHostState = remember { SnackbarHostState() }
            val coroutineScope = rememberCoroutineScope()

            logScreenView(navController, firebaseAnalytics)

            if (showPermissionDeniedSnackbar) {
                coroutineScope.launch {
                    val result = snackbarHostState.showSnackbar(
                        message = "더 나은 서비스를 위해 알림 권한을 설정해 주세요.",
                        actionLabel = "설정",
                        duration = SnackbarDuration.Short,
                    )

                    if (result == SnackbarResult.ActionPerformed) {
                        startActivity(
                            Intent(ACTION_APP_NOTIFICATION_SETTINGS)
                                .putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
                        )
                    }
                }
            }

            BaekyoungTheme {
                val networkState by networkObserver.networkState.collectAsStateWithLifecycle()
                if (networkState == NetworkState.NOT_CONNECTED) {
                    BaekyoungDialog(
                        title = stringResource(id = string.network_dialog_title),
                        description = stringResource(id = string.network_dialog_description),
                        leftButtonText = stringResource(R.string.cancel),
                        rightButtonText = stringResource(id = string.setting),
                        onLeftButtonClick = { finish() },
                        onRightButtonClick = { startActivity(Intent(ACTION_WIFI_SETTINGS)) },
                    )
                }

                Scaffold(
                    snackbarHost = { SnackbarHost(snackbarHostState) },
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

    override fun onResume() {
        super.onResume()
        networkObserver.checkNetworkState()
    }

    override fun onDestroy() {
        super.onDestroy()
        networkObserver.unsubscribeNetworkCallback()
    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) !=
                PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}

private fun logScreenView(
    navController: NavController,
    firebaseAnalytics: FirebaseAnalytics,
) {
    navController.addOnDestinationChangedListener { _, destination, _ ->
        val params = Bundle()
        params.putString(SCREEN_VIEW, destination.route)
        firebaseAnalytics.logEvent(SCREEN_NAME, params)
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
    mentorChattingNavigationRoute() -> setBottomBarState(false)
    mentoringMentorNavigationRoute -> setBottomBarState(false)
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

                BottomNavigationItem(
                    selected = (currentRoute == destination.route),
                    modifier = Modifier.background(Color.Transparent),
                    onClick = { onNavigateToDestination(destination) },
                    selectedContentColor = if (currentRoute == com.tgyuu.feature.home.navigation.homeNavigationRoute) {
                        BaekyoungTheme.colors.blueFB
                    } else {
                        BaekyoungTheme.colors.black
                    },
                    unselectedContentColor = if (currentRoute == com.tgyuu.feature.home.navigation.homeNavigationRoute) {
                        BaekyoungTheme.colors.blueFB
                    } else {
                        BaekyoungTheme.colors.gray95
                    },
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
