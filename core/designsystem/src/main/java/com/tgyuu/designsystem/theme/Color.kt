package com.tgyuu.designsystem.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White

val WhiteFF = Color(0xFFF2F5FF)
val GrayCF = Color(0XFFCFCFCF)
val GrayF5 = Color(0xFFF5F5F5)
val GrayF4 = Color(0xFFF4F4F4)
val GrayD0 = Color(0xFFD0D0D0)
val GrayAC = Color(0xFFACACAC)
val Gray95 = Color(0xFF959595)
val Black56 = Color(0xFF565656)
val Blue5FF = Color(0xFFEAF5FF)
val BlueF5FF = Color(0xFFC6F5FF)
val BlueEFF = Color(0xFFC8EEFF)
val BlueF8 = Color(0xFFA6DFF8)
val Blue4E = Color(0xFF001F4E)
val BlueD5FF = Color(0xFFA8D5FF)
val BlueFFF2 = Color(0xFF99FFF2)
val BlueFF2 = Color(0xFF819FF2)
val Blue45 = Color(0xFF0E1B45)
val BlueEDFF = Color(0xFF99EDFF)
val BlueFF = Color(0xFF85A0FF)
val BlueCFF = Color(0xFF759CFF)
val Blue3FF = Color(0xFFB2E3FF)
val BlueD7 = Color(0xFF6584D7)
val BlueDD = Color(0xFF0F9EDD)
val BlueFB = Color(0xFF7A9DFB)
val Blue00 = Color(0xFF0038FF)
val Blue94 = Color(0XFF204094)
val Blue37 = Color(0xFF375299)
val Purple = Color(0xFF765FFF)

@Stable
class BaekyoungColor(
    white: Color = White,
    whiteFF: Color = WhiteFF,
    black: Color = Black,
    black56: Color = Black56,
    blue37: Color = Blue37,
    blue00: Color = Blue00,
    blueFF2: Color = BlueFF2,
    blueFFF2: Color = BlueFFF2,
    blue45: Color = Blue45,
    blueFF: Color = BlueFF,
    blueCFF: Color = BlueCFF,
    blue3FF: Color = Blue3FF,
    blue4E: Color = Blue4E,
    blueFB: Color = BlueFB,
    blueEDFF: Color = BlueEDFF,
    blue5FF: Color = Blue5FF,
    blueF5FF: Color = BlueF5FF,
    blueD5FF: Color = BlueD5FF,
    blueDD: Color = BlueDD,
    blueD7: Color = BlueD7,
    blueF8: Color = BlueF8,
    blue94: Color = Blue94,
    blueEFF: Color = BlueEFF,
    gray95: Color = Gray95,
    grayAC: Color = GrayAC,
    grayD0: Color = GrayD0,
    grayF4: Color = GrayF4,
    grayF5: Color = GrayF5,
    grayCF: Color = GrayCF,
    purple: Color = Purple,
) {
    var white by mutableStateOf(white)
        private set
    var whiteFF by mutableStateOf(whiteFF)
        private set
    var black by mutableStateOf(black)
        private set
    var black56 by mutableStateOf(black56)
        private set
    var blue37 by mutableStateOf(blue37)
        private set
    var blue00 by mutableStateOf(blue00)
        private set
    var blueFF by mutableStateOf(blueFF)
        private set
    var blueCFF by mutableStateOf(blueCFF)
        private set
    var blue3FF by mutableStateOf(blue3FF)
        private set
    var blueFB by mutableStateOf(blueFB)
        private set
    var blue45 by mutableStateOf(blue45)
        private set
    var blue4E by mutableStateOf(blue4E)
        private set
    var blueFF2 by mutableStateOf(blueFF2)
        private set
    var blueEDFF by mutableStateOf(blueEDFF)
        private set
    var blueFFF2 by mutableStateOf(blueFFF2)
        private set
    var blue5FF by mutableStateOf(blue5FF)
        private set
    var blueF5FF by mutableStateOf(blueF5FF)
        private set
    var blueD5FF by mutableStateOf(blueD5FF)
        private set
    var blueEFF by mutableStateOf(blueEFF)
        private set
    var blueF8 by mutableStateOf(blueF8)
        private set
    var blueDD by mutableStateOf(blueDD)
        private set
    var blueD7 by mutableStateOf(blueD7)
        private set
    var gray95 by mutableStateOf(gray95)
        private set
    var blue94 by mutableStateOf(blue94)
        private set
    var grayF4 by mutableStateOf(grayF4)
        private set
    var grayF5 by mutableStateOf(grayF5)
        private set
    var grayAC by mutableStateOf(grayAC)
        private set
    var grayCF by mutableStateOf(grayCF)
        private set
    var grayD0 by mutableStateOf(grayD0)
        private set
    var purple by mutableStateOf(purple)
        private set
}
