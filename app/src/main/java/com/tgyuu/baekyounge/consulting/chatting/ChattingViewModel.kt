package com.tgyuu.baekyounge.consulting.chatting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tgyuu.domain.usecase.consulting.PostUserChattingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChattingViewModel @Inject constructor(
    private val postUserChattingUseCase: PostUserChattingUseCase,
) : ViewModel() {
    private val _chatText: MutableStateFlow<String> = MutableStateFlow("")
    val chatText get() = _chatText.asStateFlow()

    private val _searchText: MutableStateFlow<String> = MutableStateFlow("")
    val searchText get() = _searchText.asStateFlow()

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
        if (_chatText.value.isEmpty()) {
            return@launch
        }

        postUserChattingUseCase(
            userId = _userId.value,
            chat = _chatText.value,
        ).fold(
            onSuccess = { _chatText.value = "" },
            onFailure = { Log.d("test", "메세지를 전송하는 데 실패하였습니다.") },
        )
    }
}
