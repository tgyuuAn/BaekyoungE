package com.tgyuu.baekyoung_i.consulting.consultinginformation

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tgyuu.baekyoung_i.R
import com.tgyuu.baekyoung_i.consulting.consultinginformation.component.ConsultingTextField
import com.tgyuu.baekyoung_i.main.navigation.TopLevelDestination.CONSULTING
import com.tgyuu.common.util.UiState
import com.tgyuu.designsystem.component.BaekyoungTopAppBar
import com.tgyuu.designsystem.component.Loader
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun ConsultingRoute(
    viewModel: ConsultingViewModel = hiltViewModel(),
    navigateToChatting: () -> Unit,
) {
    val grade by viewModel.grade.collectAsStateWithLifecycle()
    val major by viewModel.major.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.event.collect { event ->
            when (event) {
                is ConsultingEvent.NavigateToChatting -> navigateToChatting()
                is ConsultingEvent.ShowSnackBar -> showToast(event.message, context)
            }
        }
    }

    ConsultingScreen(
        grade = grade,
        major = major,
        uiState = uiState,
        onGradeValueChanged = viewModel::setGrade,
        onMajorValueChanged = viewModel::setMajor,
        postConsultingInformation = viewModel::postConsultingInformation,
    )
}

@Composable
internal fun ConsultingScreen(
    grade: String,
    major: String,
    uiState: UiState,
    onGradeValueChanged: (String) -> Unit,
    onMajorValueChanged: (String) -> Unit,
    postConsultingInformation: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BaekyoungTheme.colors.white)
    ) {

        Log.d("text",uiState.toString())

        if (uiState is UiState.Loading) {
            Loader(modifier = Modifier.fillMaxSize())
        }

        Image(
            painterResource(id = R.drawable.ic_consulting_bbugong),
            contentDescription = null,
            alpha = 0.3F,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 56.dp)
        )

        Image(
            painterResource(id = R.drawable.ic_consulting_baekyoung),
            contentDescription = null,
            alpha = 0.3F,
            modifier = Modifier.align(Alignment.BottomEnd)
        )

        Column(modifier = Modifier.fillMaxSize()) {
            BaekyoungTopAppBar(CONSULTING.titleTextId)

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 40.dp)
            ) {
                Text(
                    text = stringResource(R.string.consulting_inforamtion_input),
                    style = BaekyoungTheme.typography.contentNormal.copy(fontSize = 22.sp),
                    color = BaekyoungTheme.colors.gray95,
                    textAlign = TextAlign.Start,
                )

                ConsultingTextField(
                    title = stringResource(R.string.major),
                    value = major,
                    onValueChanged = onMajorValueChanged,
                    modifier = Modifier.padding(top = 25.dp),
                )

                ConsultingTextField(
                    title = stringResource(R.string.grade),
                    value = grade,
                    onValueChanged = onGradeValueChanged,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.padding(top = 20.dp),
                )

                val buttonModifier = Modifier
                    .padding(top = 50.dp, bottom = 50.dp)
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(shape = RoundedCornerShape(8.dp))

                if (grade.isNotEmpty() && major.isNotEmpty()) {
                    Box(
                        modifier = buttonModifier
                            .background(BaekyoungTheme.colors.blueF8)
                            .clickable { postConsultingInformation() }
                    ) {
                        Text(
                            text = stringResource(R.string.start_consulting),
                            textAlign = TextAlign.Center,
                            style = BaekyoungTheme.typography.contentBig,
                            color = BaekyoungTheme.colors.white,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                } else {
                    Box(
                        modifier = buttonModifier.background(
                            BaekyoungTheme.colors.gray95.copy(
                                alpha = 0.8F
                            )
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.start_consulting),
                            textAlign = TextAlign.Center,
                            style = BaekyoungTheme.typography.contentBig,
                            color = BaekyoungTheme.colors.white,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

private fun showToast(text: String, context: Context) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}
