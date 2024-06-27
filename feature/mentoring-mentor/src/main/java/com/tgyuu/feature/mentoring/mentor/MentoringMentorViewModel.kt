package com.tgyuu.feature.mentoring.mentor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tgyuu.common.util.UiState
import com.tgyuu.common.util.generateNowDateTime
import com.tgyuu.common.util.toISOLocalDateTimeString
import com.tgyuu.domain.usecase.auth.GetUserInformationUseCase
import com.tgyuu.domain.usecase.chatting.GetMentorChattingRoomUseCase
import com.tgyuu.domain.usecase.mentoring.DeleteMentorInfoUseCase
import com.tgyuu.domain.usecase.mentoring.GetMentorInfoUseCase
import com.tgyuu.domain.usecase.mentoring.PostMentorInfoUseCase
import com.tgyuu.model.auth.UserInformation
import com.tgyuu.model.chatting.JoinChat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MentoringMentorViewModel @Inject constructor(
    private val getUserInformationUseCase: GetUserInformationUseCase,
    private val postMentorInfoUseCase: PostMentorInfoUseCase,
    private val deleteMentorInfoUseCase: DeleteMentorInfoUseCase,
    private val getMentorInfoUseCase: GetMentorInfoUseCase,
    private val getMentorChattingRoomUseCase: GetMentorChattingRoomUseCase,
) : ViewModel() {
    private val _userInformation = MutableStateFlow(UserInformation())

    private val _checked = MutableStateFlow<Boolean>(false)
    val checked = _checked.asStateFlow()

    private val _chattingRooms = MutableStateFlow<UiState<List<JoinChat>>>(UiState.Loading)
    val chattingRooms = _chattingRooms.asStateFlow()

    init {
        getUserInformation()
    }

    private fun getUserInformation() = viewModelScope.launch {
        getUserInformationUseCase("-1")
            .onSuccess {
                _userInformation.value = it
                getMentorInfo()
                getAllChattingRoom()
            }
            .onFailure { }
    }

    private fun getMentorInfo() = viewModelScope.launch {
        getMentorInfoUseCase(userId = _userInformation.value.userId)
            .onSuccess { setChecked(true) }
            .onFailure { setChecked(false) }
    }

    private fun getAllChattingRoom() = viewModelScope.launch {
        getMentorChattingRoomUseCase(userId = _userInformation.value.userId)
            .onSuccess { _chattingRooms.value = UiState.Success(it) }
            .onFailure { _chattingRooms.value = UiState.Error(it.message ?: "알 수 없는 요청입니다.") }
    }

    fun registerMentorInfo() = viewModelScope.launch {
        postMentorInfoUseCase(
            userId = _userInformation.value.userId,
            nickName = _userInformation.value.nickName,
            registrationDate = generateNowDateTime().toISOLocalDateTimeString(),
        ).onSuccess { setChecked(true) }
            .onFailure { setChecked(false) }
    }

    fun deleteMentorInfo() = viewModelScope.launch {
        deleteMentorInfoUseCase(userId = _userInformation.value.userId)
            .onSuccess { setChecked(false) }
            .onFailure { setChecked(true) }
    }

    private fun setChecked(boolean: Boolean) {
        _checked.value = boolean
    }
}
