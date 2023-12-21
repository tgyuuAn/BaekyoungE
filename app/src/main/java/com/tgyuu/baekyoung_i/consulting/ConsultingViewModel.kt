package com.tgyuu.baekyoung_i.consulting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pknu.domain.usecase.consulting.PostConsultingInformationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
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

    fun setGrade(grade: String) {
        _grade.value = grade
        Log.d("test", _grade.value.toString())
    }

    fun setMajor(major: String) {
        _major.value = major
        Log.d("test", _major.value)
    }

    fun postConsultingInformation() = viewModelScope.launch {
        _grade.value.toIntOrNull()?.let { it ->
            postConsultingInformationUseCase(
                grade = it, major = _major.value
            )
        } ?: Log.d("test", "Error!")
    }
}