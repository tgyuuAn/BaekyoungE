package com.tgyuu.feature.storage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
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
    Scaffold(
        topBar = { BaekyoungTopBar(titleTextId = R.string.storage) },
        containerColor = BaekyoungTheme.colors.grayF5,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            HorizontalDivider(color = BaekyoungTheme.colors.grayDC)

            var showSpinner by remember { mutableStateOf(false) }

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
                    modifier = Modifier.clickable { showSpinner = !showSpinner },
                )
            }

            if (showSpinner) {
                LazyColumn(
                    modifier = Modifier
                        .padding(start = 20.dp, top = 5.dp)
                        .shadow(elevation = 10.dp, shape = RoundedCornerShape(8.dp))
                        .clip(RoundedCornerShape(8.dp))
                        .background(BaekyoungTheme.colors.white),
                ) {
                    itemsIndexed(yearList) { index, year ->
                        Column(
                            modifier = Modifier
                                .width(IntrinsicSize.Min)
                                .clickable {
                                    setSelectedYear(year)
                                    showSpinner = false
                                },
                        ) {
                            Text(
                                text = "$year 년",
                                style = BaekyoungTheme.typography.labelRegular.copy(fontSize = 13.sp),
                                color = BaekyoungTheme.colors.gray95,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(horizontal = 20.dp, vertical = 6.dp)
                                    .requiredWidth(IntrinsicSize.Max),
                            )

                            if (index < yearList.size - 1) {
                                HorizontalDivider(color = BaekyoungTheme.colors.grayAC)
                            }
                        }
                    }
                }
            }
        }
    }
}
