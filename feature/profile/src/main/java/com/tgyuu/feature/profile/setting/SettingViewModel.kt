package com.tgyuu.feature.profile.setting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.user.UserApiClient
import com.tgyuu.common.util.UiState
import com.tgyuu.domain.usecase.auth.GetUserInformationUseCase
import com.tgyuu.domain.usecase.auth.PostUserInformationUseCase
import com.tgyuu.domain.usecase.auth.UpdateUserInformationUseCase
import com.tgyuu.model.auth.UserInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val getUserInformationUseCase: GetUserInformationUseCase,
    private val updateUserInformationUseCase: UpdateUserInformationUseCase,
) : ViewModel() {
    private val _eventFlow = MutableSharedFlow<SettingEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _userInformation = MutableStateFlow<UiState<UserInformation>>(UiState.Loading)
    val userInformation = _userInformation.asStateFlow()

    private val _newNickname = MutableStateFlow("")
    val newNickname = _newNickname.asStateFlow()

    private val _newMajor = MutableStateFlow("")
    val newMajor = _newMajor.asStateFlow()

    private val _newGrade = MutableStateFlow(1)

    private val userInfo =
        MutableStateFlow(UserInformation("", "", "", "", -1, ""))

    init {
        checkTokenExists()
    }

    fun event(event: SettingEvent) = viewModelScope.launch { _eventFlow.emit(event) }

    fun setNewNickname(nickname: String) {
        _newNickname.value = nickname
    }

    fun clearNewNickname() {
        Log.d("test", "호출 ${_newNickname.value}")
        _newNickname.value = ""
    }

    fun setNewMajor(major: String) {
        _newMajor.value = major
    }

    fun clearNewMajor() {
        Log.d("test", "호출 ${_newMajor.value}")
        _newMajor.value = ""
    }

    fun setNewGrade(grade: Int) {
        _newGrade.value = grade
    }

    fun updateNewGrade() = viewModelScope.launch {
        updateUserInformationUseCase(
            userId = userInfo.value.userId,
            nickName = userInfo.value.nickName,
            gender = userInfo.value.gender,
            grade = _newGrade.value,
            major = userInfo.value.major,
            registrationDate = userInfo.value.registrationDate,
        ).onSuccess { event(SettingEvent.UpdateSuccess) }
            .onFailure { event(SettingEvent.UpdateFailed("학년 업데이트에 실패하였습니다.")) }
    }

    fun updateNewNickname() = viewModelScope.launch {
        updateUserInformationUseCase(
            userId = userInfo.value.userId,
            nickName = _newNickname.value,
            gender = userInfo.value.gender,
            grade = userInfo.value.grade,
            major = userInfo.value.major,
            registrationDate = userInfo.value.registrationDate,
        ).onSuccess { event(SettingEvent.UpdateSuccess) }
            .onFailure { event(SettingEvent.UpdateFailed("닉네임 업데이트에 실패하였습니다.")) }
    }

    fun updateNewMajor() = viewModelScope.launch {
        updateUserInformationUseCase(
            userId = userInfo.value.userId,
            nickName = userInfo.value.nickName,
            gender = userInfo.value.gender,
            grade = userInfo.value.grade,
            major = _newMajor.value,
            registrationDate = userInfo.value.registrationDate,
        ).onSuccess { event(SettingEvent.UpdateSuccess) }
            .onFailure { event(SettingEvent.UpdateFailed("학과 업데이트에 실패하였습니다.")) }
    }

    fun checkTokenExists() = viewModelScope.launch {
        if (!AuthApiClient.instance.hasToken()) {
            _userInformation.value = UiState.Error("유저 정보가 없습니다.")
            return@launch
        }

        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                _userInformation.value = UiState.Error("유저 정보가 없습니다.")
            }

            getUserInformation(tokenInfo?.id ?: -1)
            return@accessTokenInfo
        }
    }

    fun getUserInformation(userId: Long) = viewModelScope.launch {
        getUserInformationUseCase(userId.toString())
            .onSuccess {
                _userInformation.value = UiState.Success(it)
                userInfo.value = it
            }
            .onFailure { _userInformation.value = UiState.Error("유저 정보가 없습니다.") }
    }

    sealed class SettingEvent {
        data object UpdateSuccess : SettingEvent()
        data class UpdateFailed(val message: String) : SettingEvent()
    }
}
