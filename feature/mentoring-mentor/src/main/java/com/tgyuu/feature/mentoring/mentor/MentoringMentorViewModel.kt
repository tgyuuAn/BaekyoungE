package com.tgyuu.feature.mentoring.mentor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tgyuu.common.util.generateNowDateTime
import com.tgyuu.common.util.toISOLocalDateTimeString
import com.tgyuu.domain.usecase.auth.GetUserInformationUseCase
import com.tgyuu.domain.usecase.chatting.GetRemoteAllChattingRoomUseCase
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
    private val getRemoteAllChattingRoomUseCase: GetRemoteAllChattingRoomUseCase,
) : ViewModel() {
    private val _userInformation = MutableStateFlow(UserInformation())

    private val _checked = MutableStateFlow<Boolean>(false)
    val checked = _checked.asStateFlow()

    private val _chattingRooms = MutableStateFlow<List<JoinChat>>(listOf())
    val chattingRooms = _chattingRooms.asStateFlow()

    init {
        getUserInformation(-1)
    }

    fun getUserInformation(userId: Long) = viewModelScope.launch {
        getUserInformationUseCase(userId.toString())
            .onSuccess {
                _userInformation.value = it
                getMentorInfo()
                getAllChattingRoom()
            }
            .onFailure { }
    }

    fun getMentorInfo() = viewModelScope.launch {
        getMentorInfoUseCase(userId = _userInformation.value.userId)
            .onSuccess { setChecked(true) }
            .onFailure { setChecked(false) }
    }

    fun getAllChattingRoom() = viewModelScope.launch {
        getRemoteAllChattingRoomUseCase(userId = _userInformation.value.userId)
            .onSuccess { _chattingRooms.value = it }
            .onFailure { }
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
