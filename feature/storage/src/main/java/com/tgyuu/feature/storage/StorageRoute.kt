package com.tgyuu.feature.storage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tgyuu.designsystem.R.drawable
import com.tgyuu.designsystem.component.BaekyoungTopBar
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun StorageRoute(viewModel: StorageViewModel = hiltViewModel()) {
    val yearList by viewModel.yearList.collectAsStateWithLifecycle()
    val selectedYear by viewModel.selectedYear.collectAsStateWithLifecycle()

    StorageScreen(
        yearList = yearList,
        selectedYear = selectedYear,
        setSelectedYear = viewModel::setSelectedYear,
    )
}

@Composable
internal fun StorageScreen(
    yearList: List<String>,
    selectedYear: String,
    setSelectedYear: (String) -> Unit,
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { BaekyoungTopBar(titleTextId = R.string.storage) },
        containerColor = BaekyoungTheme.colors.grayF5,
        modifier = Modifier
            .fillMaxSize()
            .clickable { showBottomSheet = false },
    ) { paddingValues ->
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
                            .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
                            .clip(RoundedCornerShape(10.dp))
                            .background(BaekyoungTheme.colors.white),
                    ) {
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

        if (showBottomSheet) {

        }
    }
}
