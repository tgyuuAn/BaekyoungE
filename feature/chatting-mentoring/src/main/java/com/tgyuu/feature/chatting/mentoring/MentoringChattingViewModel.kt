package com.tgyuu.feature.chatting.mentoring

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tgyuu.common.util.UiState
import com.tgyuu.domain.usecase.auth.GetUserInformationUseCase
import com.tgyuu.domain.usecase.chatting.PostMentoringMessageUseCase
import com.tgyuu.model.auth.UserInformation
import com.tgyuu.model.chatting.AiMessage
import com.tgyuu.model.chatting.ChattingRole
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MentoringChattingViewModel @Inject constructor(
    private val getUserInformationUseCase: GetUserInformationUseCase,
    private val postMentoringMessageUseCase: PostMentoringMessageUseCase,
) : ViewModel() {
    private val _chatText: MutableStateFlow<String> = MutableStateFlow("")
    val chatText get() = _chatText.asStateFlow()

    private val _searchText: MutableStateFlow<String> = MutableStateFlow("")
    val searchText get() = _searchText.asStateFlow()

    val chatLog: SnapshotStateList<AiMessage> = mutableStateListOf()

    private val _chatState: MutableStateFlow<UiState<Unit>> =
        MutableStateFlow(UiState.Success(Unit))
    val chatState = _chatState.asStateFlow()

    val roomId: MutableStateFlow<String> = MutableStateFlow("")

    private val _userInformation = MutableStateFlow(UserInformation())

    init {
        getUserInformation(-1)
    }

    fun setChatText(chatText: String) {
        _chatText.value = chatText
    }

    fun setSearchText(searchText: String) {
        _searchText.value = searchText
    }

    fun getUserInformation(userId: Long) = viewModelScope.launch {
        getUserInformationUseCase(userId.toString())
            .onSuccess { _userInformation.value = it }
            .onFailure { }
    }

    fun sendMessage() = viewModelScope.launch {
        if (_chatText.value.isEmpty()) {
            return@launch
        }

        chatLog.add(
            AiMessage(
                content = _chatText.value,
                role = ChattingRole.USER,
            ),
        )
        _chatState.value = UiState.Loading

        postMentoringMessageUseCase(
            roomId = roomId.value,
            userId = _userInformation.value.userId,
            content = _chatText.value,
        )
            .onSuccess { _chatText.value = "" }
            .onFailure { Log.d("test", "onFailure : " + it.toString()) }
            .also { _chatState.value = UiState.Success(Unit) }
    }
}
