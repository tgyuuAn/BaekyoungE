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
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tgyuu.designsystem.theme.BaekKyoungTypoGraphy
import com.tgyuu.designsystem.theme.BaekKyoungTypogrphy
import com.tgyuu.designsystem.theme.Blue00
import com.tgyuu.designsystem.theme.Blue37

@Composable
fun AuthScreen(
    id: String,
    password: String,
    idChanged: (String) -> Unit,
    passwordChanged: (String) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_auth_bbugong),
            contentDescription = stringResource(id = R.string.bbugong_auth_conext_descriptioin),
            modifier = Modifier
                .align(Alignment.TopEnd)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
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
                    color = Blue37,
                    shape = RoundedCornerShape(7.dp)
                )

            Text(
                text = stringResource(R.string.id),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Blue37,
                modifier = Modifier.align(alignment = Alignment.Start)
            )

            OutlinedTextField(
                value = id,
                onValueChange = idChanged,
                singleLine = true,
                textStyle = TextStyle.Default.copy(fontSize = 16.sp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Blue37,
                    unfocusedBorderColor = Blue37,
                ),
                modifier = modifier,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_login),
                        contentDescription = "",
                        tint = Blue37
                    )
                },
            )

            Text(
                text = stringResource(R.string.password),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Blue37,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .align(alignment = Alignment.Start)
            )

            OutlinedTextField(
                value = password,
                onValueChange = passwordChanged,
                singleLine = true,
                textStyle = TextStyle.Default.copy(fontSize = 16.sp),
                visualTransformation = PasswordVisualTransformation(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Blue37,
                    unfocusedBorderColor = Blue37,
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_password),
                        contentDescription = "",
                        tint = Blue37
                    )
                },
                modifier = modifier.padding(top = 10.dp)
            )

            Box(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth()
                    .height(70.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(Blue00.copy(alpha = 0.6F))
            ) {
                Text(
                    text = stringResource(R.string.login),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Text(
                text = stringResource(R.string.sign_up),
                color = Blue00.copy(alpha = 0.5F),
                style = BaekKyoungTypoGraphy().gangwonBold,
                modifier = Modifier.padding(top = 20.dp),
            )
        }

        Image(
            painter = painterResource(id = R.drawable.ic_auth_baekyoung),
            contentDescription = stringResource(id = R.string.bbugong_auth_conext_descriptioin),
            modifier = Modifier.align(Alignment.BottomStart)
        )
    }
}