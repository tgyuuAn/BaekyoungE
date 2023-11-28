package com.tgyuu.designsystem.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun BaekyoungTheme(
    content: @Composable () -> Unit,
) {
    val colors = BaekyoungColor()
    val typography = BaekKyoungTypoGraphy()
    CompositionLocalProvider(
        LocalBaekyoungColors provides colors,
        LocalBaekyoungTypography provides typography,
    ) {
        MaterialTheme(content = content)
    }
}

object BaekyoungTheme {
    val colors: BaekyoungColor @Composable get() = LocalBaekyoungColors.current
    val typography: BaekKyoungTypogrphy @Composable get() = LocalBaekyoungTypography.current
}

private val LocalBaekyoungColors = staticCompositionLocalOf<BaekyoungColor> {
    error("Any WappColors Did Not Provided")
}

private val LocalBaekyoungTypography = staticCompositionLocalOf<BaekKyoungTypogrphy> {
    error("Any WappTypography Did Not Provided")
}
