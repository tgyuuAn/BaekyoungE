package com.tgyuu.baekyoung_i.etc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tgyuu.baekyoung_i.R
import com.tgyuu.baekyoung_i.etc.component.SettingBar
import com.tgyuu.baekyoung_i.etc.component.SettingTitleBar
import com.tgyuu.baekyoung_i.main.navigation.TopLevelDestination.ETC
import com.tgyuu.designsystem.component.BaekyoungTopAppBar
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
fun EtcScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BaekyoungTheme.colors.white)
    ) {
        BaekyoungTopAppBar(ETC.titleTextId)
        Divider(color = BaekyoungTheme.colors.purple)
        SettingTitleBar(
            title = "계정 설정",
            leadIconId = R.drawable.ic_account_setting,
        )
        Divider(color = BaekyoungTheme.colors.purple)
        SettingBar(text = "로그아웃")
        SettingBar(text = "회원 탈퇴")
        Divider(color = BaekyoungTheme.colors.purple)
        SettingTitleBar(
            title = "문의 및 정책",
            leadIconId = R.drawable.ic_inquiry_policy,
        )
        Divider(color = BaekyoungTheme.colors.purple)
        SettingBar(text = "문의 하기")
        SettingBar(text = "FAQ")
        SettingBar(text = "약관 및 정책")
        SettingBar(text = "개인정보 처리 방침")
    }
}