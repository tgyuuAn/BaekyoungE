package com.tgyuu.baekyounge.consulting.consultinginformation

import androidx.lifecycle.ViewModel
import com.pknu.domain.usecase.consulting.PostConsultingInformationUseCase
import com.tgyuu.common.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ConsultingViewModel @Inject constructor(
    private val postConsultingInformationUseCase: PostConsultingInformationUseCase,
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Unit>> = MutableStateFlow(UiState.Init)
    val uiState get() = _uiState.asStateFlow()

    private val _event: MutableSharedFlow<ConsultingEvent> = MutableSharedFlow()
    val event get() = _event.asSharedFlow()

    fun setUiState(uiState: UiState<Unit>) {
        _uiState.value = uiState
    }
}
