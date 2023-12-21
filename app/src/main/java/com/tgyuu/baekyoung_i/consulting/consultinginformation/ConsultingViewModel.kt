package com.tgyuu.baekyoung_i.consulting.consultinginformation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pknu.domain.usecase.consulting.PostConsultingInformationUseCase
import com.tgyuu.common.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConsultingViewModel @Inject constructor(
    private val postConsultingInformationUseCase: PostConsultingInformationUseCase,
) : ViewModel() {

    private val _grade: MutableStateFlow<String> = MutableStateFlow("")
    val grade get() = _grade.asStateFlow()

    private val _major: MutableStateFlow<String> = MutableStateFlow("")
    val major get() = _major.asStateFlow()

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Nothing)
    val uiState get() = _uiState.asStateFlow()

    private val _event: MutableSharedFlow<ConsultingEvent> = MutableSharedFlow()
    val event get() = _event.asSharedFlow()

    fun setGrade(grade: String) {
        _grade.value = grade
    }

    fun setMajor(major: String) {
        _major.value = major
    }

    fun setUiState(uiState: UiState) {
        _uiState.value = uiState
    }

    fun postConsultingInformation() = viewModelScope.launch {

        setUiState(UiState.Loading)

        _grade.value.toIntOrNull()?.let {
            postConsultingInformationUseCase(
                grade = it, major = _major.value
            ).fold(
                onSuccess = { _event.emit(ConsultingEvent.NavigateToChatting) },
                onFailure = { _event.emit(ConsultingEvent.ShowSnackBar("상담 정보를 보내는 데 실패 하였습니다.")) },
            )
        } ?: _event.emit(ConsultingEvent.ShowSnackBar("학년 정보를 숫자로 입력해주세요."))

        setUiState(UiState.Nothing)
    }

}