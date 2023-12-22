package com.tgyuu.designsystem.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White

val GrayCF = Color(0XFFCFCFCF)
val GrayF4 = Color(0xFFF4F4F4)
val Gray95 = Color(0xFF959595)
val Blue5FF = Color(0xFFEAF5FF)
val BlueEFF = Color(0xFFC8EEFF)
val BlueF8 = Color(0xFFA6DFF8)
val BlueD5FF = Color(0xFFA8D5FF)
val BlueFF = Color(0xFF85A0FF)
val Blue00 = Color(0xFF0038FF)
val Blue37 = Color(0xFF375299)
val Purple = Color(0xFF765FFF)

@Stable
class BaekyoungColor(
    white: Color = White,
    black: Color = Black,
    blue37: Color = Blue37,
    blue00: Color = Blue00,
    blueFF: Color = BlueFF,
    blue5FF: Color = Blue5FF,
    blueD5FF: Color = BlueD5FF,
    blueF8: Color = BlueF8,
    blueEFF: Color = BlueEFF,
    gray95: Color = Gray95,
    grayF4: Color = GrayF4,
    grayCF: Color = GrayCF,
    purple: Color = Purple,
) {
    var white by mutableStateOf(white)
        private set
    var black by mutableStateOf(black)
        private set
    var blue37 by mutableStateOf(blue37)
        private set
    var blue00 by mutableStateOf(blue00)
        private set
    var blueFF by mutableStateOf(blueFF)
        private set
    var blue5FF by mutableStateOf(blue5FF)
        private set
    var blueD5FF by mutableStateOf(blueD5FF)
        private set
    var blueEFF by mutableStateOf(blueEFF)
        private set
    var blueF8 by mutableStateOf(blueF8)
        private set
    var gray95 by mutableStateOf(gray95)
        private set
    var grayF4 by mutableStateOf(grayF4)
        private set
    var grayCF by mutableStateOf(grayCF)
        private set
    var purple by mutableStateOf(purple)
        private set
}