package com.tgyuu.baekyoung_i.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tgyuu.baekyoung_i.R
import com.tgyuu.designsystem.theme.BaekyoungTheme
import com.tgyuu.designsystem.theme.Blue00
import com.tgyuu.designsystem.theme.Blue37

@Composable
internal fun AuthRoute(navigateToHome: () -> Unit) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val idChanged: (String) -> Unit = { textValue ->
        id = textValue
    }

    val passwordChanged: (String) -> Unit = { textValue ->
        password = textValue
    }

    AuthScreen(
        id = id,
        password = password,
        idChanged = idChanged,
        passwordChanged = passwordChanged,
        navigateToHome = navigateToHome,
    )
}

@Composable
fun AuthScreen(
    id: String,
    password: String,
    idChanged: (String) -> Unit,
    passwordChanged: (String) -> Unit,
    navigateToHome: () -> Unit,
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
                style = BaekyoungTheme.typography.contentBig,
                color = BaekyoungTheme.colors.blue37,
                modifier = Modifier.align(alignment = Alignment.Start)
            )

            OutlinedTextField(
                value = id,
                onValueChange = idChanged,
                singleLine = true,
                textStyle = TextStyle.Default.copy(fontSize = 16.sp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BaekyoungTheme.colors.blue37,
                    unfocusedBorderColor = BaekyoungTheme.colors.blue37,
                ),
                modifier = modifier,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_login),
                        contentDescription = stringResource(id = R.string.auth_id_description),
                        tint = BaekyoungTheme.colors.blue37
                    )
                },
            )

            Text(
                text = stringResource(R.string.password),
                style = BaekyoungTheme.typography.contentBig,
                color = BaekyoungTheme.colors.blue37,
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
                    focusedBorderColor = BaekyoungTheme.colors.blue37,
                    unfocusedBorderColor = BaekyoungTheme.colors.blue37,
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_password),
                        contentDescription = stringResource(id = R.string.auth_password_description),
                        tint = BaekyoungTheme.colors.blue37
                    )
                },
                modifier = modifier,
            )

            Box(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth()
                    .height(70.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(Blue00.copy(alpha = 0.6F))
                    .clickable { navigateToHome() }
            ) {
                Text(
                    text = stringResource(R.string.login),
                    textAlign = TextAlign.Center,
                    style = BaekyoungTheme.typography.contentBig,
                    color = BaekyoungTheme.colors.white,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Text(
                text = stringResource(R.string.sign_up),
                color = BaekyoungTheme.colors.blue00.copy(alpha = 0.5F),
                style = BaekyoungTheme.typography.contentNormal,
                modifier = Modifier.padding(top = 10.dp),
            )
        }

        Image(
            painter = painterResource(id = R.drawable.ic_auth_baekyoung),
            contentDescription = stringResource(id = R.string.bbugong_auth_conext_descriptioin),
            modifier = Modifier.align(Alignment.BottomStart)
        )
    }
}