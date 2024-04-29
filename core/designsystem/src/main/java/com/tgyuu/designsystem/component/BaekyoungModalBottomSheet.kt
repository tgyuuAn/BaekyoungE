package com.tgyuu.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.tgyuu.common.util.addFocusCleaner
import com.tgyuu.designsystem.theme.BaekyoungTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaekyoungModalBottomSheet(
    sheetState: SheetState,
    onDissmissRequest: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    val focusManager = LocalFocusManager.current

    ModalBottomSheet(
        onDismissRequest = onDissmissRequest,
        sheetState = sheetState,
        containerColor = BaekyoungTheme.colors.white,
        dragHandle = {},
        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
    ) {
        Column(modifier = Modifier.addFocusCleaner(focusManager)) {
            content()
        }
    }
}
