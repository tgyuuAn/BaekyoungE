package com.tgyuu.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tgyuu.common.util.UiState
import com.tgyuu.designsystem.R
import com.tgyuu.designsystem.component.Loader
import com.tgyuu.designsystem.theme.BaekyoungTheme
import com.tgyuu.feature.profile.R.drawable
import com.tgyuu.feature.profile.R.string
import com.tgyuu.model.auth.UserInformation
import com.tgyuu.model.storage.ChattingRoom

@Composable
internal fun ProfileRoute(
    navigateToSetting: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val userInformationState by viewModel.userInformation.collectAsStateWithLifecycle()
    val chattingRooms by viewModel.chattingLogs.collectAsStateWithLifecycle()

    ProfileScreen(
        userInformationState = userInformationState,
        chattingRooms = chattingRooms,
        navigateToSetting = navigateToSetting,
    )
}

@Composable
fun ProfileScreen(
    userInformationState: UiState<UserInformation>,
    chattingRooms: List<ChattingRoom>,
    navigateToSetting: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BaekyoungTheme.colors.white),
    ) {
        when (userInformationState) {
            is UiState.Loading -> Loader(modifier = Modifier.fillMaxSize())
            is UiState.Success -> {
                val userInformation = userInformationState.data
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                ) {
                    Text(
                        text = generateUserNameSpan(userName = userInformation.nickName),
                        style = BaekyoungTheme.typography.labelBold,
                        modifier = Modifier.align(Alignment.CenterStart),
                    )

                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            ) {  navigateToSetting() },
                    )
                }

                HorizontalDivider(color = BaekyoungTheme.colors.grayDC)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(BaekyoungTheme.colors.grayF0),
                ) {
                    Text(
                        text = generateDaysSinceRegistrationSpan(
                            days = userInformation
                                .calculateTimeSinceRegistration()
                                .toDays()
                                .toString(),
                        ),
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
                                text = chattingRooms.size.toString(),
                                style = BaekyoungTheme.typography.contentBold,
                                modifier = Modifier.padding(start = 5.dp),
                            )
                        }
                    }

                    Text(
                        text = stringResource(id = string.completed_consulation),
                        textAlign = TextAlign.Center,
                        style = BaekyoungTheme.typography.labelRegular,
                        modifier = Modifier
                            .padding(bottom = 30.dp)
                            .fillMaxWidth(),
                    )
                }

                HorizontalDivider(color = BaekyoungTheme.colors.grayDC)

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp),
                ) {
                    Image(
                        painter = painterResource(id = drawable.ic_suggestion_box),
                        contentDescription = null,
                    )

                    Text(
                        text = stringResource(id = string.suggestion_box),
                        style = BaekyoungTheme.typography.labelRegular.copy(fontSize = 10.sp),
                        color = BaekyoungTheme.colors.gray95,
                        modifier = Modifier.padding(top = 4.dp),
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                HorizontalDivider(
                    color = BaekyoungTheme.colors.grayDC,
                    modifier = Modifier.padding(bottom = 60.dp),
                )
            }

            is UiState.Error -> {
                // Todo
            }
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

@Composable
private fun generateDaysSinceRegistrationSpan(days: String): AnnotatedString =
    buildAnnotatedString {
        append("함께한 지 ")
        withStyle(
            style = SpanStyle(
                color = BaekyoungTheme.colors.black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            ),
        ) {
            append(days)
        }
        append(" 일")
    }
