package com.tgyuu.feature.storage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tgyuu.domain.usecase.chatting.DeleteChattingRoomUseCase
import com.tgyuu.domain.usecase.chatting.GetAllChattingLogUseCase
import com.tgyuu.model.storage.ChattingRoom
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val getAllChattingLogUseCase: GetAllChattingLogUseCase,
    private val deleteChattingRoomUseCase: DeleteChattingRoomUseCase,
) : ViewModel() {
    private val _selectedYear = MutableStateFlow("2024")
    val selectedYear = _selectedYear.asStateFlow()

    private val _chattingLogs = MutableStateFlow<List<ChattingRoom>>(listOf())
    val chattingLogs = _chattingLogs.asStateFlow()

    init {
        getAllChattingLogs()
    }

    fun getAllChattingLogs() = viewModelScope.launch {
        getAllChattingLogUseCase()
            .onSuccess { _chattingLogs.value = it }
            .onFailure { Log.d("test", "로컬 데이터 호출 실패") }
    }

    fun deleteChattingRoom(roomId: String) = viewModelScope.launch {
        deleteChattingRoomUseCase(roomId)
            .onSuccess { }
            .onFailure { Log.d("test", "로컬 데이터 삭제 실패") }
    }
}
