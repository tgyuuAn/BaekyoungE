package com.tgyuu.feature.mentoring.mentor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tgyuu.common.util.generateNowDateTime
import com.tgyuu.common.util.toISOLocalDateTimeString
import com.tgyuu.domain.usecase.mentoring.DeleteMentorInfoUseCase
import com.tgyuu.domain.usecase.mentoring.GetMentorInfoUseCase
import com.tgyuu.domain.usecase.mentoring.PostMentorInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MentoringMentorViewModel @Inject constructor(
    private val postMentorInfoUseCase: PostMentorInfoUseCase,
    private val deleteMentorInfoUseCase: DeleteMentorInfoUseCase,
    private val getMentorInfoUseCase: GetMentorInfoUseCase,
) : ViewModel() {
    private val _checked = MutableStateFlow<Boolean>(false)
    val checked = _checked.asStateFlow()

    init{
        getMentorInfo()
    }

    private fun setChecked(boolean: Boolean) {
        _checked.value = boolean
    }

    fun registerMentorInfo() = viewModelScope.launch {
        postMentorInfoUseCase(
            userId = "test",
            nickName = "",
            registrationDate = generateNowDateTime().toISOLocalDateTimeString(),
        ).onSuccess { setChecked(true) }
            .onFailure { setChecked(false) }
    }

    fun deleteMentorInfo() = viewModelScope.launch {
        deleteMentorInfoUseCase(userId = "test")
            .onSuccess { setChecked(false) }
            .onFailure { setChecked(true) }
    }

    fun getMentorInfo() = viewModelScope.launch {
        deleteMentorInfoUseCase(userId = "test")
            .onSuccess { setChecked(true) }
            .onFailure { setChecked(false) }
    }
}
