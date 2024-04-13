package com.tgyuu.feature.consulting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.user.UserApiClient
import com.tgyuu.domain.usecase.auth.VerifyMemberIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConsultingViewModel @Inject constructor(
    private val verifyMemberIdUseCase: VerifyMemberIdUseCase,
) : ViewModel() {
    private val _eventFlow: MutableSharedFlow<ConsultingEvent> = MutableSharedFlow()
    val eventFlow get() = _eventFlow.asSharedFlow()

    fun event(event: ConsultingEvent) = viewModelScope.launch { _eventFlow.emit(event) }

    fun navigateToChatting() {
        // 토큰이 없을 경우 바로 Auth 페이지로 이동
        if (!AuthApiClient.instance.hasToken()) {
            event(ConsultingEvent.ShowSnackBar(ERROR_USER_MESSAGE))
            return
        }

        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error == null) {
                verifyMemberId(tokenInfo?.id ?: -1)
            }
        }
    }

    fun verifyMemberId(userId: Long) = viewModelScope.launch {
        verifyMemberIdUseCase(userId)
            .onSuccess { event(ConsultingEvent.NavigateToChatting(userId.toString())) }
            .onFailure { event(ConsultingEvent.ShowSnackBar(ERROR_USER_MESSAGE)) }
    }

    sealed class ConsultingEvent {
        data class NavigateToChatting(val userId: String) : ConsultingEvent()
        data class ShowSnackBar(val message: String) : ConsultingEvent()
    }

    companion object {
        private const val ERROR_USER_MESSAGE = "유저 정보가 없습니다."
    }
}
