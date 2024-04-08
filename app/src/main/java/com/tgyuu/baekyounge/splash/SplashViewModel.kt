package com.tgyuu.baekyounge.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.user.UserApiClient
import com.tgyuu.domain.usecase.auth.VerifyMemberIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val verifyMemberIdUseCase: VerifyMemberIdUseCase,
) : ViewModel() {
    private val _eventFlow = MutableSharedFlow<SplashEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _canSkipSplash = MutableStateFlow(false)
    val canSkipSplash = _canSkipSplash.asStateFlow()

    init {
        viewModelScope.launch {
            delay(3000L)
            _canSkipSplash.value = true
        }
    }

    fun event(event: SplashEvent) = viewModelScope.launch { _eventFlow.emit(event) }

    fun checkTokenExists() {
        // 토큰이 없을 경우 바로 Auth 페이지로 이동
        if (!AuthApiClient.instance.hasToken()) {
            event(SplashEvent.NavigateToAuth)
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
            .onSuccess { event(SplashEvent.NavigateToHome) }
            .onFailure { event(SplashEvent.NavigateToAuth) }
    }

    sealed class SplashEvent {
        data object NavigateToAuth : SplashEvent()
        data object NavigateToHome : SplashEvent()
    }
}
