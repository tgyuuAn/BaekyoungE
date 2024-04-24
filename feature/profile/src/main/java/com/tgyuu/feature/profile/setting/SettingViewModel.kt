package com.tgyuu.feature.profile.setting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.user.UserApiClient
import com.tgyuu.common.util.UiState
import com.tgyuu.domain.usecase.auth.GetUserInformationUseCase
import com.tgyuu.model.auth.UserInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val getUserInformationUseCase: GetUserInformationUseCase,
) : ViewModel() {
    private val _userInformation = MutableStateFlow<UiState<UserInformation>>(UiState.Loading)
    val userInformation = _userInformation.asStateFlow()

    private val _newNickname = MutableStateFlow("")
    val newNickname = _newNickname.asStateFlow()

    private val _newMajor = MutableStateFlow("")
    val newMajor = _newMajor.asStateFlow()

    private val _newGrade = MutableStateFlow(1)

    init {
        checkTokenExists()
    }

    fun setNewNickname(nickname: String) {
        _newNickname.value = nickname
    }

    fun clearNewNickname(){
        Log.d("test", "호출 ${_newNickname.value}")
        _newNickname.value = ""
    }

    fun setNewMajor(major: String) {
        _newMajor.value = major
    }

    fun clearNewMajor(){
        Log.d("test", "호출 ${_newMajor.value}")
        _newMajor.value = ""
    }

    fun setNewGrade(grade: Int) {
        _newGrade.value = grade
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
            }
            .onFailure { _userInformation.value = UiState.Error("유저 정보가 없습니다.") }
    }
}
