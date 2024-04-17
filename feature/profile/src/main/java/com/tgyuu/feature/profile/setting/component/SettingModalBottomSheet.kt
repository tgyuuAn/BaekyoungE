package com.tgyuu.feature.profile.setting.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.tgyuu.designsystem.theme.BaekyoungTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingModalBottomSheet(
    sheetState: SheetState,
    onDissmissRequest: () -> Unit,
    innerContent: @Composable ColumnScope.() -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDissmissRequest,
        sheetState = sheetState,
        containerColor = BaekyoungTheme.colors.white,
        dragHandle = {},
        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
    ) {
        innerContent()
    }
}
