package com.tgyuu.feature.profile.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.user.UserApiClient
import com.tgyuu.common.util.UiState
import com.tgyuu.domain.usecase.auth.DeleteUserInformationUseCase
import com.tgyuu.domain.usecase.auth.GetUserInformationUseCase
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
    private val deleteUserInformationUseCase: DeleteUserInformationUseCase,
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
        _newNickname.value = ""
    }

    fun setNewMajor(major: String) {
        _newMajor.value = major
    }

    fun clearNewMajor() {
        _newMajor.value = ""
    }

    fun setNewGrade(grade: Int) {
        _newGrade.value = grade
    }

    fun updateNewGrade() = updateUserInfo(newGrade = _newGrade.value)

    fun updateNewNickname() = updateUserInfo(newNickname = _newNickname.value)

    fun updateNewMajor() = updateUserInfo(newMajor = _newMajor.value)

    private fun updateUserInfo(
        newGrade: Int? = null,
        newNickname: String? = null,
        newMajor: String? = null,
    ) = viewModelScope.launch {
        val updatedUserInformation = UserInformation(
            userId = userInfo.value.userId,
            nickName = newNickname ?: userInfo.value.nickName,
            gender = userInfo.value.gender,
            grade = newGrade ?: userInfo.value.grade,
            major = newMajor ?: userInfo.value.major,
            registrationDate = userInfo.value.registrationDate,
        )

        updateUserInformationUseCase(
            userId = updatedUserInformation.userId,
            nickName = updatedUserInformation.nickName,
            gender = updatedUserInformation.gender,
            grade = updatedUserInformation.grade,
            major = updatedUserInformation.major,
            registrationDate = updatedUserInformation.registrationDate,
        ).onSuccess {
            event(SettingEvent.UpdateSuccess)
            _userInformation.value = UiState.Success(updatedUserInformation)
            userInfo.value = updatedUserInformation
        }.onFailure { throwable ->
            event(SettingEvent.UpdateFailed("정보 업데이트에 실패하였습니다."))
        }
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

    fun logoutKakao() = UserApiClient.instance.logout { error ->
        if (error != null) {
            event(SettingEvent.LogoutFailed("로그아웃에 실패하였습니다."))
        } else {
            event(SettingEvent.LogoutSuccess)
        }
    }

    fun withdrawalKakao() = viewModelScope.launch {
        deleteUserInformationUseCase(userInfo.value.userId)
            .onSuccess {
                UserApiClient.instance.unlink { error ->
                    if (error != null) {
                        event(SettingEvent.LogoutFailed("회원 탈퇴에 실패하였습니다."))
                    } else {
                        event(SettingEvent.LogoutSuccess)
                    }
                }
            }
            .onFailure { event(SettingEvent.LogoutFailed("회원 탈퇴에 실패하였습니다.")) }
    }

    sealed class SettingEvent {
        data object UpdateSuccess : SettingEvent()
        data class UpdateFailed(val message: String) : SettingEvent()
        data class LogoutFailed(val message: String) : SettingEvent()
        data object LogoutSuccess : SettingEvent()
    }
}
