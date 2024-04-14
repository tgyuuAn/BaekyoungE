package com.tgyuu.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                painter = painterResource(id = com.tgyuu.designsystem.R.drawable.ic_arrow_right),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterEnd),
            )
        }
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
