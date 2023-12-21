package com.tgyuu.baekyoung_i.consulting

import androidx.lifecycle.ViewModel
import com.pknu.domain.usecase.consulting.GetConsultingChattingUseCase
import com.pknu.domain.usecase.consulting.PostConsultingInformationUseCase
import com.pknu.domain.usecase.consulting.PostUserChattingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ConsultingViewModel @Inject constructor(
    private val postUserChattingUseCase: PostUserChattingUseCase,
    private val postConsultingInformationUseCase: PostConsultingInformationUseCase,
    private val getConsultingChattingUseCase: GetConsultingChattingUseCase,
) : ViewModel() {

    private val _grade: MutableStateFlow<Int> = MutableStateFlow(0)
    val grade get() = _grade.asStateFlow()

    private val _major: MutableStateFlow<String> = MutableStateFlow("")
    val major get() = _major.asStateFlow()

    fun setGrade(grade: Int) {
        _grade.value = grade
    }

    fun setMajor(major: String) {
        _major.value = major
    }
}