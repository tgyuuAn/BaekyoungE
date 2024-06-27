package com.tgyuu.feature.mentoring.mentee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tgyuu.common.util.UiState
import com.tgyuu.domain.usecase.auth.GetUserInformationUseCase
import com.tgyuu.domain.usecase.chatting.GetMenteeChattingRoomUseCase
import com.tgyuu.model.auth.UserInformation
import com.tgyuu.model.chatting.JoinChat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MentoringMenteeViewModel @Inject constructor(
    private val getUserInformationUseCase: GetUserInformationUseCase,
    private val getMenteeChattingRoomUseCase: GetMenteeChattingRoomUseCase,
) : ViewModel() {
    private val _userInformation = MutableStateFlow(UserInformation())

    private val _chattingRooms = MutableStateFlow<UiState<List<JoinChat>>>(UiState.Loading)
    val chattingRooms = _chattingRooms.asStateFlow()

    fun getUserInformation(userId: Long) = viewModelScope.launch {
        getUserInformationUseCase(userId.toString())
            .onSuccess {
                _userInformation.value = it
                getMenteeChattingRoom()
            }
            .onFailure { }
    }

    private fun getMenteeChattingRoom() = viewModelScope.launch {
        getMenteeChattingRoomUseCase(_userInformation.value.userId)
            .onSuccess { _chattingRooms.value = UiState.Success(it) }
            .onFailure { _chattingRooms.value = UiState.Error(it.message ?: "알 수 없는 요청입니다.") }
    }
}
