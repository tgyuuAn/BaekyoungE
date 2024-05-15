package com.tgyuu.feature.consulting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.user.UserApiClient
import com.tgyuu.common.util.UiState
import com.tgyuu.domain.usecase.auth.GetUserInformationUseCase
import com.tgyuu.model.auth.UserInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConsultingViewModel @Inject constructor(
    private val getUserInformationUseCase: GetUserInformationUseCase,
) : ViewModel() {
    private val _eventFlow: MutableSharedFlow<ConsultingEvent> = MutableSharedFlow()
    val eventFlow get() = _eventFlow.asSharedFlow()

    private val _userInformation = MutableStateFlow<UiState<UserInformation>>(UiState.Loading)
    val userInformation = _userInformation.asStateFlow()

    init {
        checkTokenExists()
    }

    fun event(event: ConsultingEvent) = viewModelScope.launch { _eventFlow.emit(event) }

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
            .onSuccess { _userInformation.value = UiState.Success(it) }
            .onFailure { _userInformation.value = UiState.Error(it.message ?: "유저 정보가 없습니다.") }
    }

    fun navigateToChatting() = viewModelScope.launch {
        when (val uiState = _userInformation.value) {
            is UiState.Success -> {
                val userId = uiState.data.userId
                event(ConsultingEvent.NavigateToChatting(userId))
            }

            else -> {
                event(ConsultingEvent.ShowSnackBar(ERROR_USER_MESSAGE))
            }
        }
    }

    sealed class ConsultingEvent {
        data class NavigateToChatting(val userId: String) : ConsultingEvent()
        data class ShowSnackBar(val message: String) : ConsultingEvent()
    }

    companion object {
        private const val ERROR_USER_MESSAGE = "유저 정보가 없습니다."
    }
}
