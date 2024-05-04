package com.tgyuu.feature.mentoring

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MentoringViewModel @Inject constructor() : ViewModel() {
    private val _selectedRule = MutableStateFlow<MentorMenteeRule>(MentorMenteeRule.NOTHING)
    val selectedRule = _selectedRule.asStateFlow()

    fun setSelectedRule(rule: MentorMenteeRule) {
        _selectedRule.value = rule
    }
}

enum class MentorMenteeRule {
    MENTOR, MENTEE, NOTHING
}
