package com.tgyuu.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tgyuu.designsystem.R

val gangwonBold: FontFamily =
    FontFamily(
        Font(
            resId = R.font.gangwon_bold,
            weight = FontWeight.Bold,
            style = FontStyle.Normal,
        )
    )

val gangwonLight: FontFamily =
    FontFamily(
        Font(
            resId = R.font.gangwon_light,
            weight = FontWeight.Medium,
            style = FontStyle.Normal,
        )
    )

val teunteunNormal: FontFamily =
    FontFamily(
        Font(
            resId = R.font.gangwon_teunteun,
            weight = FontWeight.Normal,
            style = FontStyle.Normal,
        )
    )

val teunteunBold: FontFamily =
    FontFamily(
        Font(
            resId = R.font.gangwon_teunteun,
            weight = FontWeight.Bold,
            style = FontStyle.Normal,
        )
    )

@Stable
class BaekKyoungTypogrphy internal constructor(
    contentNormal: TextStyle,
    contentBig: TextStyle,
    titleNormal: TextStyle,
) {
    var gangwonBold: TextStyle by mutableStateOf(gangwonBold)
        private set
    var gangwonLight: TextStyle by mutableStateOf(gangwonLight)
        private set
}

@Composable
fun BaekKyoungTypoGraphy(): BaekKyoungTypogrphy {
    return BaekKyoungTypogrphy(
        contentNormal = TextStyle(
            fontSize = 16.sp,
            fontFamily = teunteunNormal,
        ),
        contentBig = TextStyle(
            fontSize = 20.sp,
            fontFamily = teunteunBold,
        ),
        titleNormal = TextStyle(
            fontSize = 30.sp,
            fontFamily = teunteunNormal
        )
    )
}