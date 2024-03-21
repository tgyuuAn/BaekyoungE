package com.tgyuu.baekyoung_i.consulting.consultinginformation

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

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
){

}

private fun showToast(text: String, context: Context) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}
