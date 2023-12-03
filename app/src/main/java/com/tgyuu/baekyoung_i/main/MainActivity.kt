package com.tgyuu.baekyoung_i.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.tgyuu.baekyoung_i.auth.AuthScreen
import com.tgyuu.designsystem.theme.BaekyoungTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var id by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            val idChanged : (String) -> Unit = { textValue ->
                id = textValue
            }

            val passwordChanged : (String) -> Unit = { textValue ->
                password = textValue
            }

            BaekyoungTheme {
                AuthScreen(id, password, idChanged, passwordChanged)
            }
        }
    }
}