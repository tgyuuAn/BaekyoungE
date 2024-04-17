package com.tgyuu.feature.profile

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
class ProfileViewModel @Inject constructor(
    private val verifyMemberIdUseCase: VerifyMemberIdUseCase,
) : ViewModel() {
    private val _eventFlow = MutableSharedFlow<ProfileEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun checkTokenExists() {
        // 토큰이 없을 경우 바로 Auth 페이지로 이동
        if (!AuthApiClient.instance.hasToken()) {
            event(ProfileEvent.UserLoadingFailed)
            return
        }

        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                event(ProfileEvent.UserLoadingFailed)
            }

            verifyMemberId(tokenInfo?.id ?: -1)
            return@accessTokenInfo
        }
    }

    fun verifyMemberId(userId: Long) = viewModelScope.launch {
        verifyMemberIdUseCase(userId)
            .onSuccess { event(ProfileEvent.UserLoadingSuccess) }
            .onFailure { event(ProfileEvent.UserLoadingFailed) }
    }

    fun event(event: ProfileEvent) = viewModelScope.launch { _eventFlow.emit(event) }

    sealed class ProfileEvent {
        data object UserLoadingFailed : ProfileEvent()
        data object UserLoadingSuccess : ProfileEvent()
    }
}
