package com.tgyuu.baekyoung_i.etc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tgyuu.baekyoung_i.R
import com.tgyuu.baekyoung_i.etc.component.SettingBar
import com.tgyuu.baekyoung_i.etc.component.SettingTitleBar
import com.tgyuu.baekyoung_i.main.navigation.TopLevelDestination.ETC
import com.tgyuu.designsystem.component.BaekyoungTopBar
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun EtcRoute() {
    EtcScreen()
}

@Composable
fun EtcScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BaekyoungTheme.colors.white)
    ) {
        val dividerColor = BaekyoungTheme.colors.blueFF

        BaekyoungTopBar(ETC.titleTextId)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BaekyoungTheme.colors.white)
        ) {
            SettingTitleBar(
                title = stringResource(id = R.string.setting_account),
                leadIconId = R.drawable.ic_account_setting,
            )
            SettingBar(text = stringResource(id = R.string.logout))
            SettingBar(text = stringResource(id = R.string.withdrawal))
            Divider(color = dividerColor)
            SettingTitleBar(
                title = stringResource(id = R.string.inquriy_and_policy),
                leadIconId = R.drawable.ic_inquiry_policy,
            )
            Divider(color = dividerColor)
            SettingBar(text = stringResource(id = R.string.inquriy))
            SettingBar(text = stringResource(id = R.string.faq))
            SettingBar(text = stringResource(id = R.string.policy))
            SettingBar(text = stringResource(id = R.string.private_policy))
        }
    }
}