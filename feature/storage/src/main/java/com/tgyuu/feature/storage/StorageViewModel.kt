package com.tgyuu.feature.storage

import androidx.lifecycle.ViewModel
import com.tgyuu.model.storage.ChattingLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor() : ViewModel() {
    private val _selectedYear = MutableStateFlow("2024")
    val selectedYear = _selectedYear.asStateFlow()

    private val _chattingLogs = MutableStateFlow(
        listOf(
            ChattingLog(
                "3월 8일 오후 04:35",
                "지금 머릿속에 떠오르는 모든 고민을 의식의 " +
                    "흐름대로 적어보기",
            ),
            ChattingLog(
                "4월 22일 오후 01:35",
                "지금 머릿속에 떠오르는 모든 고민을 의식의 " +
                    "흐름대로 적어보기",
            ),
        ),
    )
    val chattingLogs = _chattingLogs.asStateFlow()

    fun setSelectedYear(year: String) {
        _selectedYear.value = year
    }
}
