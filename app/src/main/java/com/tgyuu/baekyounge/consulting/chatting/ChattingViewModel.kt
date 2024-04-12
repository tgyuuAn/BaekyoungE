package com.tgyuu.baekyounge.consulting.chatting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tgyuu.domain.usecase.consulting.ai.PostChatMessageUseCase
import com.tgyuu.model.consulting.ChatLog
import com.tgyuu.model.consulting.ChattingRole
import com.tgyuu.model.consulting.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChattingViewModel @Inject constructor(
    private val postChatMessageUseCase: PostChatMessageUseCase,
) : ViewModel() {
    private val _chatText: MutableStateFlow<String> = MutableStateFlow("")
    val chatText get() = _chatText.asStateFlow()

    private val _searchText: MutableStateFlow<String> = MutableStateFlow("")
    val searchText get() = _searchText.asStateFlow()

    private val _chatLog: MutableStateFlow<ChatLog> = MutableStateFlow(ChatLog(mutableListOf()))
    val chatLog = _chatLog.asStateFlow()

    private val _userId: MutableStateFlow<String> = MutableStateFlow("")

    fun setChatText(chatText: String) {
        _chatText.value = chatText
    }

    fun setSearchText(searchText: String) {
        _searchText.value = searchText
    }

    fun setUserId(userId: String) {
        _userId.value = userId
    }

    fun postUserChatting() = viewModelScope.launch {
        Log.d("test", "postUserChatting 호출")

        if (_chatText.value.isEmpty()) {
            return@launch
        }

        Log.d("test", "postUserChatting 호출")

        _chatLog.value.messages.add(
            Message(
                content = _chatText.value,
                role = ChattingRole.USER,
            ),
        )

        postChatMessageUseCase(_chatLog.value)
            .onSuccess {
                Log.d("test", "onSuccess : " + it.toString())

                _chatLog.value = it.copy()
                _chatText.value = ""
            }
            .onFailure {
                Log.d("test", "onFailure : " + it.toString())
            }
    }
}
