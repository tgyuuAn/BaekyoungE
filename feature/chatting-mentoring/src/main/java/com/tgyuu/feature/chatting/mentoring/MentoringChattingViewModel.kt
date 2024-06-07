package com.tgyuu.feature.chatting.mentoring

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tgyuu.common.util.UiState
import com.tgyuu.common.util.generateNowDateTime
import com.tgyuu.common.util.toISOLocalDateTimeString
import com.tgyuu.domain.usecase.auth.GetUserInformationUseCase
import com.tgyuu.domain.usecase.chatting.GetPreviousMentoringMessagesUseCase
import com.tgyuu.domain.usecase.chatting.PostMentoringMessageUseCase
import com.tgyuu.domain.usecase.chatting.SubscribeMentoringMessagesUseCase
import com.tgyuu.model.auth.UserInformation
import com.tgyuu.model.chatting.MentoringMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@HiltViewModel
class MentoringChattingViewModel @Inject constructor(
    private val getUserInformationUseCase: GetUserInformationUseCase,
    private val postMentoringMessageUseCase: PostMentoringMessageUseCase,
    private val getPreviousMentoringMessagesUseCase: GetPreviousMentoringMessagesUseCase,
    private val subscribeMentoringMessagesUseCase: SubscribeMentoringMessagesUseCase,
) : ViewModel() {
    private val _chatText: MutableStateFlow<String> = MutableStateFlow("")
    val chatText get() = _chatText.asStateFlow()

    private val _searchText: MutableStateFlow<String> = MutableStateFlow("")
    val searchText get() = _searchText.asStateFlow()

    val chatLog: SnapshotStateList<MentoringMessage> = mutableStateListOf()

    private val _chatState: MutableStateFlow<UiState<Unit>> =
        MutableStateFlow(UiState.Success(Unit))
    val chatState = _chatState.asStateFlow()

    private val _roomId: MutableStateFlow<String> = MutableStateFlow("")

    private val _userInformation = MutableStateFlow(UserInformation())
    val userInformation = _userInformation.asStateFlow()

    private val _isFirstPage = MutableStateFlow(false)
    val isFirstPage = _isFirstPage.asStateFlow()

    val _pagingTimeStamp = MutableStateFlow(generateNowDateTime().toISOLocalDateTimeString())

    val isLoading = AtomicBoolean(false)

    init {
        getUserInformation(-1)
    }

    fun setRoomId(roomId: String) {
        _roomId.value = roomId
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

        _chatState.value = UiState.Loading

        postMentoringMessageUseCase(
            roomId = _roomId.value,
            userId = userInformation.value.userId,
            content = _chatText.value,
        )
            .onSuccess { _chatText.value = "" }
            .onFailure { Log.d("test", "onFailure : " + it.toString()) }
            .also { _chatState.value = UiState.Success(Unit) }
    }

    fun getPreviousMessages() {
        viewModelScope.launch {
            if (!isLoading.get()) {
                isLoading.getAndSet(true)
                getPreviousMentoringMessagesUseCase(
                    _roomId.value,
                    _pagingTimeStamp.value,
                )
                    .onSuccess {
                        if (it.size < PAGING_SIZE) {
                            _isFirstPage.value = true
                        }

                        chatLog.addAll(0, it)

                        if (it.size != 0) {
                            _pagingTimeStamp.value = chatLog[0].createdAt
                        }
                    }
                    .onFailure { Log.d("test", "onFailure : " + it.toString()) }
                    .also { isLoading.getAndSet(false) }

            }
        }
    }

    fun subscribeMessages() = viewModelScope.launch {
        subscribeMentoringMessagesUseCase(_roomId.value).collect { chatLog.add(it) }
    }

    companion object{
        private const val PAGING_SIZE = 30
    }

}
