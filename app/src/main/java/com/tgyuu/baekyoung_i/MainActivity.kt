package com.tgyuu.baekyoung_i

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tgyuu.baekyoung_i.ui.theme.BaekyoungiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaekyoungiTheme {
                AuthScreen()
            }
        }
    }
}