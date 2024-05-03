package com.tgyuu.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.user.UserApiClient
import com.tgyuu.common.util.UiState
import com.tgyuu.domain.usecase.auth.GetUserInformationUseCase
import com.tgyuu.domain.usecase.chatting.GetAllChattingLogUseCase
import com.tgyuu.model.auth.UserInformation
import com.tgyuu.model.storage.ChattingRoom
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserInformationUseCase: GetUserInformationUseCase,
    private val getAllChattingLogUseCase: GetAllChattingLogUseCase,
) : ViewModel() {
    private val _userInformation = MutableStateFlow<UiState<UserInformation>>(UiState.Loading)
    val userInformation = _userInformation.asStateFlow()

    private val _chattingLogs = MutableStateFlow<List<ChattingRoom>>(listOf())
    val chattingLogs = _chattingLogs.asStateFlow()

    init {
        checkTokenExists()
        getAllChattingLogs()
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

    private fun getAllChattingLogs() = viewModelScope.launch {
        getAllChattingLogUseCase()
            .onSuccess { _chattingLogs.value = it }
            .onFailure { }
    }

    fun getUserInformation(userId: Long) = viewModelScope.launch {
        getUserInformationUseCase(userId.toString())
            .onSuccess {
                _userInformation.value = UiState.Success(it)
            }
            .onFailure { _userInformation.value = UiState.Error("유저 정보가 없습니다.") }
    }
}
