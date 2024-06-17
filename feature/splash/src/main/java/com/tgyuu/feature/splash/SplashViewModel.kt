package com.tgyuu.feature.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.user.UserApiClient
import com.tgyuu.common.util.getFCMToken
import com.tgyuu.domain.usecase.auth.GetUserInformationUseCase
import com.tgyuu.domain.usecase.auth.UpdateUserInformationUseCase
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
    private val updateUserInformationUseCase: UpdateUserInformationUseCase,
    private val getUserInformationUseCase: GetUserInformationUseCase,
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
            if (error != null) {
                event(SplashEvent.NavigateToAuth)
            }

            verifyMemberId(tokenInfo?.id.toString())
            return@accessTokenInfo
        }
    }

    fun verifyMemberId(userId: String) = viewModelScope.launch {
        verifyMemberIdUseCase(userId)
            .onSuccess {
                getUserInformationUseCase(userId)
                    .onSuccess {
                        updateUserInformationUseCase(
                            userId = it.userId,
                            nickName = it.nickName,
                            gender = it.gender,
                            major = it.major,
                            grade = it.grade,
                            registrationDate = it.registrationDate,
                            fcmToken = getFCMToken(),
                        )
                        event(SplashEvent.NavigateToHome)
                    }
                    .onFailure { Log.d("test", it.toString()) }
            }
            .onFailure { event(SplashEvent.NavigateToAuth) }
    }

    sealed class SplashEvent {
        data object NavigateToAuth : SplashEvent()
        data object NavigateToHome : SplashEvent()
    }
}
