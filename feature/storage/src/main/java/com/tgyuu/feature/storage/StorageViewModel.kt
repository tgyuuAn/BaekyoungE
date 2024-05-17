package com.tgyuu.feature.storage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tgyuu.domain.usecase.chatting.DeleteLocalChattingRoomUseCase
import com.tgyuu.domain.usecase.chatting.GetLocalAllChattingLogUseCase
import com.tgyuu.model.storage.ChattingRoom
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val getLocalAllChattingLogUseCase: GetLocalAllChattingLogUseCase,
    private val deleteLocalChattingRoomUseCase: DeleteLocalChattingRoomUseCase,
) : ViewModel() {
    private val _eventFlow = MutableSharedFlow<StorageEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _selectedYear = MutableStateFlow("2024")
    val selectedYear = _selectedYear.asStateFlow()

    private val _chattingLogs = MutableStateFlow<List<ChattingRoom>>(listOf())
    val chattingLogs = _chattingLogs.asStateFlow()

    fun event(event: StorageEvent) = viewModelScope.launch { _eventFlow.emit(event) }

    fun getAllChattingLogs() = viewModelScope.launch {
        getLocalAllChattingLogUseCase()
            .onSuccess { _chattingLogs.value = it }
            .onFailure { event(StorageEvent.EventFailed(it.toString())) }
    }

    fun deleteChattingRoom(roomId: String) = viewModelScope.launch {
        deleteLocalChattingRoomUseCase(roomId)
            .onSuccess {
                getAllChattingLogs()
                event(StorageEvent.DeleteSuccess)
            }
            .onFailure { event(StorageEvent.EventFailed(it.toString())) }
    }

    sealed class StorageEvent {
        data object DeleteSuccess : StorageEvent()
        data class EventFailed(val message: String) : StorageEvent()
    }
}
