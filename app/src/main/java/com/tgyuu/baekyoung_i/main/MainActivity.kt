package com.tgyuu.baekyoung_i.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.tgyuu.baekyoung_i.main.navigation.BaekyoungNavHost
import com.tgyuu.designsystem.theme.BaekyoungTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaekyoungTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = BottomNavigation()
                ){

                }
                BaekyoungNavHost(navController = navController)
            }
        }
    }
}