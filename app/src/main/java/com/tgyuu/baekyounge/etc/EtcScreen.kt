package com.tgyuu.baekyounge.etc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tgyuu.baekyounge.R
import com.tgyuu.baekyounge.etc.component.SettingBar
import com.tgyuu.baekyounge.etc.component.SettingTitleBar
import com.tgyuu.baekyounge.main.navigation.TopLevelDestination.PROFILE
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
            .background(BaekyoungTheme.colors.white),
    ) {
        val dividerColor = BaekyoungTheme.colors.blueFF

        BaekyoungTopBar(PROFILE.titleTextId)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BaekyoungTheme.colors.white),
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
