package com.tgyuu.baekyoung_i

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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

@Composable
fun AuthScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_auth_bbugong),
            contentDescription = stringResource(id = R.string.bbugong_auth_conext_descriptioin),
            modifier = Modifier
                .align(Alignment.TopEnd)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_auth_baekyoung),
            contentDescription = stringResource(id = R.string.bbugong_auth_conext_descriptioin),
            modifier = Modifier
                .align(Alignment.BottomStart)
        )
    }
}