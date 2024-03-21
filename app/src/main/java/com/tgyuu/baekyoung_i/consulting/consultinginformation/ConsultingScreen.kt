package com.tgyuu.baekyoung_i.consulting.consultinginformation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tgyuu.baekyoung_i.R
import com.tgyuu.designsystem.component.BaekyoungTopBar
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
internal fun ConsultingRoute(
    viewModel: ConsultingViewModel = hiltViewModel(),
    navigateToChatting: () -> Unit,
) {
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

    ConsultingScreen()
}

@Composable
internal fun ConsultingScreen(
) {
    Scaffold(
        topBar = {
            BaekyoungTopBar(
                titleTextId = R.string.consulting,
                titleImageId = R.drawable.ic_consulting_note,
                contentDescriptionId = R.string.consulting,
            )
        },
        contentColor = BaekyoungTheme.colors.grayF5,
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {

        }

    }
}

@Preview
@Composable
internal fun PreviewConsultingScreen(
) {
    BaekyoungTheme {
        ConsultingScreen()
    }
}

private fun showToast(text: String, context: Context) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}
