package com.tgyuu.baekyoung_i

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_auth_bbugong),
            contentDescription = stringResource(id = R.string.bbugong_auth_conext_descriptioin),
            modifier = Modifier
                .align(Alignment.TopEnd)
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 85.dp, start = 40.dp, end = 40.dp)
        ) {
            val modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 3.dp,
                    color = Color(0xFF375299),
                    shape = RoundedCornerShape(7.dp)
                )

            Text(
                text = "아이디",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF375299)
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF375299),
                    unfocusedBorderColor = Color(0xFF375299)
                ),
                modifier = modifier
            )

            Text(
                text = "비밀번호",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF375299),
                modifier = Modifier.padding(top = 10.dp)
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF375299),
                    unfocusedBorderColor = Color(0xFF375299)
                ),
                modifier = modifier.padding(top = 10.dp)
            )

            Box(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth()
                    .height(70.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(Color(0xFF0038FF).copy(alpha = 0.6F))
            ) {
                Text(
                    text = "로그인",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.ic_auth_baekyoung),
            contentDescription = stringResource(id = R.string.bbugong_auth_conext_descriptioin),
            modifier = Modifier.align(Alignment.BottomStart)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewAuthScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_auth_bbugong),
            contentDescription = stringResource(id = R.string.bbugong_auth_conext_descriptioin),
            modifier = Modifier
                .align(Alignment.TopEnd)
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(
                    bottom = 85.dp,
                    start = 40.dp,
                    end = 40.dp,
                )
        ) {
            val modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 3.dp,
                    color = Color(0xFF375299),
                    shape = RoundedCornerShape(7.dp)
                )

            Text(
                text = "아이디",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF375299)
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF375299),
                    unfocusedBorderColor = Color(0xFF375299)
                ),
                modifier = modifier,
                leadingIcon = {},
            )

            Text(
                text = "비밀번호",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF375299),
                modifier = Modifier.padding(top = 10.dp)
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF375299),
                    unfocusedBorderColor = Color(0xFF375299)
                ),
                modifier = modifier.padding(top = 10.dp)
            )

            Box(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth()
                    .height(70.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(Color(0xFF0038FF).copy(alpha = 0.6F))
            ) {
                Text(
                    text = "로그인",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.ic_auth_baekyoung),
            contentDescription = stringResource(id = R.string.bbugong_auth_conext_descriptioin),
            modifier = Modifier.align(Alignment.BottomStart)
        )
    }
}