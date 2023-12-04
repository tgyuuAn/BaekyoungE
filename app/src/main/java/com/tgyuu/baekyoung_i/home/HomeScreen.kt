package com.tgyuu.baekyoung_i.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tgyuu.baekyoung_i.R
import com.tgyuu.baekyoung_i.main.navigation.TopLevelDestination.HOME
import com.tgyuu.designsystem.component.BaekyoungTopAppBar
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .wrapContentHeight()
                .background(BaekyoungTheme.colors.blue5FF)
        ) {
            BaekyoungTopAppBar(titleTextId = HOME.titleTextId)
            WhaleBeeContents()
            HotPost()
        }
        ConsultingHistoryButton(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
private fun WhaleBeeContents() {
    Column(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .weight(1f)
                    .clickable { }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_hackers),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }

            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
                    .clickable { }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_extracurricular),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
        }

        Row {
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .weight(1f)
                    .clickable { }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_webtoon),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }

            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
                    .clickable { }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_mentoring),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}

@Composable
private fun HotPost(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 20.dp)
            .background(BaekyoungTheme.colors.white)
    ) {
    }
}

@Composable
private fun ConsultingHistoryButton(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(BaekyoungTheme.colors.blueD5FF)
            .fillMaxWidth()
            .height(60.dp)
            .clickable { }
    ) {
        Text(
            text = "내 상담내역 확인하기",
            style = BaekyoungTheme.typography.titleNormal,
            color = BaekyoungTheme.colors.white,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 20.dp)
        )
        Image(
            painter = painterResource(R.drawable.ic_right_arrow),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 20.dp)
        )
    }
}