package com.tgyuu.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

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
