package com.tgyuu.feature.storage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sd.lib.compose.wheel_picker.FVerticalWheelPicker
import com.sd.lib.compose.wheel_picker.FWheelPickerState
import com.sd.lib.compose.wheel_picker.rememberFWheelPickerState
import com.tgyuu.designsystem.R.drawable
import com.tgyuu.designsystem.component.BaekyoungModalBottomSheet
import com.tgyuu.designsystem.component.BaekyoungTopBar
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun StorageRoute(viewModel: StorageViewModel = hiltViewModel()) {
    val selectedYear by viewModel.selectedYear.collectAsStateWithLifecycle()
    val yearPickerState = rememberFWheelPickerState()

    StorageScreen(
        selectedYear = selectedYear,
        yearPickerState = yearPickerState,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun StorageScreen(
    selectedYear: String,
    yearPickerState: FWheelPickerState,
) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { BaekyoungTopBar(titleTextId = R.string.storage) },
        containerColor = BaekyoungTheme.colors.grayF5,
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        if (showBottomSheet) {
            BaekyoungModalBottomSheet(
                sheetState = sheetState,
                onDissmissRequest = { showBottomSheet = false },
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = "기록을 보고싶은 년도를 선택하세요.",
                        style = BaekyoungTheme.typography.labelBold.copy(
                            fontSize = 14.sp,
                        ),
                        color = BaekyoungTheme.colors.black,
                        modifier = Modifier.padding(top = 20.dp),
                    )

                    FVerticalWheelPicker(
                        modifier = Modifier.width(50.dp),
                        count = 1,
                        state = yearPickerState,
                    ) { year ->
                        Text(
                            text = "${year + 2024} 년",
                            style = BaekyoungTheme.typography.labelBold.copy(
                                fontSize = 14.sp,
                            ),
                            color = BaekyoungTheme.colors.black,
                        )
                    }

                    Button(
                        onClick = { showBottomSheet = false },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BaekyoungTheme.colors.gray95,
                        ),
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier.padding(
                            bottom = 20.dp,
                            start = 20.dp,
                            end = 20.dp,
                        ),
                    ) {
                        Text(
                            text = stringResource(id = R.string.complete),
                            style = BaekyoungTheme.typography.labelBold,
                            color = BaekyoungTheme.colors.white,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterVertically)
                                .padding(vertical = 8.dp),
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            HorizontalDivider(color = BaekyoungTheme.colors.grayDC)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp),
            ) {
                Text(
                    text = "$selectedYear 년",
                    style = BaekyoungTheme.typography.labelBold.copy(fontSize = 13.sp),
                    color = BaekyoungTheme.colors.gray95,
                )

                Image(
                    painter = painterResource(id = drawable.ic_spinner_arrow),
                    contentDescription = null,
                    modifier = Modifier.clickable { showBottomSheet = !showBottomSheet },
                )
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp),
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))
                            .clip(RoundedCornerShape(10.dp))
                            .background(BaekyoungTheme.colors.white),
                    ) {
                        Column(modifier = Modifier.padding(2.dp)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(horizontal = 20.dp, vertical = 15.dp),
                            ) {
                                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                    Text(
                                        text = "3월 8일 오후 04:35",
                                        style = BaekyoungTheme.typography.labelRegular.copy(fontSize = 10.sp),
                                        color = BaekyoungTheme.colors.gray95,
                                        textAlign = TextAlign.Center,
                                    )

                                    Text(
                                        text = "지금 머릿속에 떠오르는 모든 고민을 의식의 \n" +
                                            "흐름대로 적어보기",
                                        style = BaekyoungTheme.typography.labelBold,
                                        color = BaekyoungTheme.colors.black,
                                    )

                                    Text(
                                        text = " ",
                                    )
                                }

                                Spacer(modifier = Modifier.weight(0.2f))

                                Image(
                                    painter = painterResource(id = R.drawable.ic_trash_can),
                                    contentDescription = null,
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
