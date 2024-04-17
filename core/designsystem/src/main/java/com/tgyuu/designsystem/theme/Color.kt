package com.tgyuu.designsystem.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White

val Black56 = Color(0xFF565656)
val Gray95 = Color(0xFF959595)
val GrayAC = Color(0xFFACACAC)
val GrayB4 = Color(0xFFB4B4B4)
val GrayDC = Color(0xFFDCDCDC)
val GrayD0 = Color(0xFFD0D0D0)
val GrayF0 = Color(0xFFF0F0F0)
val GrayF2 = Color(0xFFF2F2F2)
val GrayF5 = Color(0xFFF5F5F5)
val Blue4E = Color(0xFF001F4E)
val BlueD7 = Color(0xFF6584D7)
val BlueDD = Color(0xFF0F9EDD)
val BlueF8 = Color(0xFFA6DFF8)
val BlueEDFF = Color(0xFF99EDFF)
val Blue5FF = Color(0xFFEAF5FF)
val BlueF5FF = Color(0xFFC6F5FF)
val Blue3FF = Color(0xFFB2E3FF)
val BlueFB = Color(0xFF7A9DFB)
val BlueCFF = Color(0xFF759CFF)
val BlueFF = Color(0xFF85A0FF)

@Stable
class BaekyoungColor(
    white: Color = White,
    black: Color = Black,
    black56: Color = Black56,
    blueFF: Color = BlueFF,
    blueCFF: Color = BlueCFF,
    blue3FF: Color = Blue3FF,
    blue4E: Color = Blue4E,
    blueFB: Color = BlueFB,
    blueEDFF: Color = BlueEDFF,
    blue5FF: Color = Blue5FF,
    blueF5FF: Color = BlueF5FF,
    blueDD: Color = BlueDD,
    blueD7: Color = BlueD7,
    blueF8: Color = BlueF8,
    gray95: Color = Gray95,
    grayAC: Color = GrayAC,
    grayD0: Color = GrayD0,
    grayDC: Color = GrayDC,
    grayF0: Color = GrayF0,
    grayF5: Color = GrayF5,
    grayF2: Color = GrayF2,
    grayB4: Color = GrayB4,
) {
    var white by mutableStateOf(white)
        private set
    var black by mutableStateOf(black)
        private set
    var black56 by mutableStateOf(black56)
        private set
    var blueFF by mutableStateOf(blueFF)
        private set
    var blueCFF by mutableStateOf(blueCFF)
        private set
    var blue3FF by mutableStateOf(blue3FF)
        private set
    var blueFB by mutableStateOf(blueFB)
        private set
    var blue4E by mutableStateOf(blue4E)
        private set
    var blueEDFF by mutableStateOf(blueEDFF)
        private set
    var blue5FF by mutableStateOf(blue5FF)
        private set
    var blueF5FF by mutableStateOf(blueF5FF)
        private set
    var blueF8 by mutableStateOf(blueF8)
        private set
    var blueDD by mutableStateOf(blueDD)
        private set
    var blueD7 by mutableStateOf(blueD7)
        private set
    var gray95 by mutableStateOf(gray95)
        private set
    var grayF2 by mutableStateOf(grayF2)
        private set
    var grayB4 by mutableStateOf(grayB4)
        private set
    var grayF5 by mutableStateOf(grayF5)
        private set
    var grayAC by mutableStateOf(grayAC)
        private set
    var grayDC by mutableStateOf(grayDC)
        private set
    var grayF0 by mutableStateOf(grayF0)
        private set
    var grayD0 by mutableStateOf(grayD0)
        private set
}
