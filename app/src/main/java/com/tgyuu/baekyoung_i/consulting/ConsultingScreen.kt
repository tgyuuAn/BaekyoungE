package com.tgyuu.baekyoung_i.consulting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tgyuu.baekyoung_i.R
import com.tgyuu.baekyoung_i.consulting.component.ConsultingTextField
import com.tgyuu.baekyoung_i.main.navigation.TopLevelDestination.CONSULTING
import com.tgyuu.designsystem.component.BaekyoungTopAppBar
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun ConsultingRoute(viewModel: ConsultingViewModel = hiltViewModel()) {
    val grade by viewModel.grade.collectAsStateWithLifecycle()
    val major by viewModel.major.collectAsStateWithLifecycle()

    ConsultingScreen(
        grade = grade,
        major = major,
        onGradeValueChanged = viewModel::setGrade,
        onMajorValueChanged = viewModel::setMajor,
    )
}

@Composable
internal fun ConsultingScreen(
    grade: String,
    major: String,
    onGradeValueChanged: (String) -> Unit,
    onMajorValueChanged: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BaekyoungTheme.colors.white)
    ) {

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
                    text = "상담정보 입력",
                    style = BaekyoungTheme.typography.contentNormal.copy(fontSize = 22.sp),
                    color = BaekyoungTheme.colors.gray95,
                    textAlign = TextAlign.Start,
                )

                ConsultingTextField(
                    title = "학과",
                    value = major,
                    onValueChanged = onMajorValueChanged,
                    modifier = Modifier.padding(top = 20.dp),
                )

                ConsultingTextField(
                    title = "학년",
                    value = grade,
                    onValueChanged = onGradeValueChanged,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.padding(top = 25.dp),
                )

                val buttonModifier = Modifier
                    .padding(top = 50.dp, bottom = 50.dp)
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(shape = RoundedCornerShape(8.dp))

                if (grade.isNotEmpty() && major.isNotEmpty()) {
                    Box(
                        modifier = buttonModifier.background(BaekyoungTheme.colors.blueF8)
                    ) {
                        Text(
                            text = "상담 시작하기",
                            textAlign = TextAlign.Center,
                            style = BaekyoungTheme.typography.contentBig,
                            color = BaekyoungTheme.colors.white,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                } else {
                    Box(
                        modifier = buttonModifier.background(BaekyoungTheme.colors.gray95.copy(alpha = 0.8F))
                    ) {
                        Text(
                            text = "상담 시작하기",
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