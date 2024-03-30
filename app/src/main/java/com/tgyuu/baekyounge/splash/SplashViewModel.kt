package com.tgyuu.baekyounge.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
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
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
                if (error == null) {
                    // Todo 로컬에 있는 토큰 값을 이용하여 파이어베이스 확인. 유저정보가 있으면 바로 홈으로 이동
                }
            }
        }

        event(SplashEvent.NavigateToAuth)
    }

    sealed class SplashEvent {
        data object NavigateToAuth : SplashEvent()
        data object NavigateToHome : SplashEvent()
    }
}
