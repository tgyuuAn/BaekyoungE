package com.tgyuu.feature.mentoring.mentor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tgyuu.common.util.generateNowDateTime
import com.tgyuu.common.util.toISOLocalDateTimeString
import com.tgyuu.domain.usecase.auth.GetUserInformationUseCase
import com.tgyuu.domain.usecase.mentoring.PostMentorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MentoringMentorViewModel @Inject constructor(
    private val postMentorUseCase: PostMentorUseCase,
) : ViewModel() {
    private val _checked = MutableStateFlow<Boolean>(false)
    val checked = _checked.asStateFlow()

    fun setChecked(boolean: Boolean) {
        _checked.value = boolean
    }

    fun registerMentorInfo() = viewModelScope.launch {
        postMentorUseCase(
            userId = "test",
            nickName = "",
            registrationDate = generateNowDateTime().toISOLocalDateTimeString(),
        ).onSuccess { setChecked(true) }
            .onFailure { setChecked(false) }
    }
}
