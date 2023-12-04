package com.tgyuu.baekyoung_i.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BaekyoungTheme.colors.blue5FF)
    ) {
        BaekyoungTopAppBar(HOME.titleTextId)
        WhaleBeeContents()
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
                modifier = Modifier.weight(1f)
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