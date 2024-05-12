package com.tgyuu.feature.mentoring.mentor

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MentoringMentorViewModel @Inject constructor() : ViewModel() {
    private val _checked = MutableStateFlow<Boolean>(false)
    val checked = _checked.asStateFlow()

    fun setChecked(boolean: Boolean) {
        _checked.value = boolean
    }
}
