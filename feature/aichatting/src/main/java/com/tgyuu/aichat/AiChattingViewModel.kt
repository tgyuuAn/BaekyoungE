package com.tgyuu.aichat

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tgyuu.common.util.UiState
import com.tgyuu.common.util.generateNowDateTime
import com.tgyuu.common.util.toISOLocalDateTimeString
import com.tgyuu.domain.usecase.chatting.GetAllChattingRoomMessagesUseCase
import com.tgyuu.domain.usecase.chatting.PostMessageUseCase
import com.tgyuu.model.consulting.ChattingRole
import com.tgyuu.model.consulting.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AiChattingViewModel @Inject constructor(
    private val postMessageUseCase: PostMessageUseCase,
    private val getAllChattingRoomMessagesUseCase: GetAllChattingRoomMessagesUseCase,
) : ViewModel() {
    private val _chatText: MutableStateFlow<String> = MutableStateFlow("")
    val chatText get() = _chatText.asStateFlow()

    private val _searchText: MutableStateFlow<String> = MutableStateFlow("")
    val searchText get() = _searchText.asStateFlow()

    val chatLog: SnapshotStateList<Message> = mutableStateListOf(
        Message(content = "안녕하세요! 무엇을 도와드릴까요 ?", role = ChattingRole.ASSISTANT),
    )

    val _chatState: MutableStateFlow<UiState<Unit>> = MutableStateFlow(UiState.Success(Unit))
    val chatState = _chatState.asStateFlow()

    private val _roomId: MutableStateFlow<String> =
        MutableStateFlow(generateNowDateTime().toISOLocalDateTimeString())

    fun setChatText(chatText: String) {
        _chatText.value = chatText
    }

    fun setSearchText(searchText: String) {
        _searchText.value = searchText
    }

    fun setRoomId(roomId: String) = viewModelScope.launch {
        if (roomId != "EMPTY") {
            _roomId.value = roomId
            getAllChattingRoomMessagesUseCase(_roomId.value).onSuccess {
                val messages = it.map {
                    Message(
                        content = it.content,
                        role = when (it.messageFrom) {
                            "USER" -> ChattingRole.USER
                            "ASSISTANT" -> ChattingRole.ASSISTANT
                            else -> ChattingRole.ASSISTANT
                        },
                    )
                }
                chatLog.addAll(messages)
            }.onFailure { }
        }
    }

    fun postUserChatting() = viewModelScope.launch {
        if (_chatText.value.isEmpty()) {
            return@launch
        }

        chatLog.add(
            Message(
                content = _chatText.value,
                role = ChattingRole.USER,
            ),
        )
        _chatText.value = ""
        _chatState.value = UiState.Loading

        postMessageUseCase(chatLog = chatLog.toList(), roomId = _roomId.value)
            .onSuccess { chatLog.addAll(it.messages) }
            .onFailure { Log.d("test", "onFailure : " + it.toString()) }
            .also { _chatState.value = UiState.Success(Unit) }
    }
}
