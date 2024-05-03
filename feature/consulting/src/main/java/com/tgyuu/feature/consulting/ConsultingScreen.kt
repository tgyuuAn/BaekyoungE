package com.tgyuu.feature.consulting

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tgyuu.common.util.UiState
import com.tgyuu.designsystem.R.string
import com.tgyuu.designsystem.component.BaekyoungButton
import com.tgyuu.designsystem.component.BaekyoungTopBar
import com.tgyuu.designsystem.component.Loader
import com.tgyuu.designsystem.theme.BaekyoungTheme
import com.tgyuu.feature.consulting.ConsultingViewModel.ConsultingEvent
import com.tgyuu.model.auth.UserInformation

@Composable
internal fun ConsultingRoute(
    viewModel: ConsultingViewModel = hiltViewModel(),
    navigateToChatting: (String) -> Unit,
) {
    val context = LocalContext.current
    val userInformationState by viewModel.userInformation.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is ConsultingEvent.NavigateToChatting -> navigateToChatting("")
                is ConsultingEvent.ShowSnackBar -> showToast(event.message, context)
            }
        }
    }

    ConsultingScreen(
        userInformationState = userInformationState,
        viewModel::navigateToChatting,
    )
}

@Composable
internal fun ConsultingScreen(
    userInformationState: UiState<UserInformation>,
    navigateToChatting: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (userInformationState) {
            is UiState.Loading -> Loader(modifier = Modifier.fillMaxSize())
            is UiState.Success -> {
                val userInformation = userInformationState.data

                Scaffold(
                    topBar = {
                        BaekyoungTopBar(
                            titleTextId = string.consulting,
                            titleImageId = R.drawable.ic_consulting_note,
                            contentDescriptionId = string.consulting,
                        )
                    },
                    containerColor = BaekyoungTheme.colors.grayF5,
                ) { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                    ) {
                        Text(
                            text = generateUserNameSpan(userInformation.nickName),
                            style = BaekyoungTheme.typography.labelRegular.copy(fontSize = 9.sp),
                            color = BaekyoungTheme.colors.gray95,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(start = 30.dp),
                        )

                        Column(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(horizontal = 40.dp),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_consulting_baekgyoung_main),
                                contentDescription = null,
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                            )

                            Text(
                                text = stringResource(id = R.string.consulting_description),
                                style = BaekyoungTheme.typography.labelRegular,
                                color = BaekyoungTheme.colors.black56,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(top = 20.dp),
                            )

                            BaekyoungButton(
                                text = R.string.navigate_to_consulting,
                                onButtonClick = { navigateToChatting() },
                                buttonColor = BaekyoungTheme.colors.black,
                                modifier = Modifier
                                    .padding(top = 30.dp)
                                    .fillMaxWidth(),
                            )
                        }
                    }
                }
            }

            else -> Unit
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

private fun showToast(text: String, context: Context) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}
