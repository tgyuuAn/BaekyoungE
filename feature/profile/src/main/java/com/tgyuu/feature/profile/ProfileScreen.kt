package com.tgyuu.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tgyuu.designsystem.R
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun ProfileRoute() {
    ProfileScreen()
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BaekyoungTheme.colors.white),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        ) {
            Text(
                text = generateUserNameSpan(userName = "종디기"),
                style = BaekyoungTheme.typography.labelBold,
                modifier = Modifier.align(Alignment.CenterStart),
            )

            Image(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterEnd),
            )
        }

        HorizontalDivider(color = BaekyoungTheme.colors.grayD0)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BaekyoungTheme.colors.whiteFF),
        ) {
            Text(
                text = "함께한 지 18 일",
                style = BaekyoungTheme.typography.labelRegular,
                modifier = Modifier.padding(top = 10.dp, start = 20.dp),
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 5.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.align(Alignment.Center),
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_consulting_note),
                        contentDescription = null,
                    )

                    Text(
                        text = "2",
                        style = BaekyoungTheme.typography.contentBold,
                        modifier = Modifier.padding(start = 5.dp),
                    )
                }
            }

            Text(
                text = "완료한 상담",
                textAlign = TextAlign.Center,
                style = BaekyoungTheme.typography.labelRegular,
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .fillMaxWidth(),
            )
        }

        HorizontalDivider(color = BaekyoungTheme.colors.grayD0)
    }
}

@Composable
private fun generateUserNameSpan(userName: String): AnnotatedString = buildAnnotatedString {
    withStyle(
        style = SpanStyle(
            color = BaekyoungTheme.colors.black,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
        ),
    ) {
        append(userName, " ")
    }
    append("님")
}
