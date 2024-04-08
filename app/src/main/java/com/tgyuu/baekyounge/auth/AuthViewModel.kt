package com.tgyuu.baekyounge.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.user.UserApiClient
import com.tgyuu.domain.usecase.auth.VerifyMemberIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val verifyMemberIdUseCase: VerifyMemberIdUseCase,
) : ViewModel() {
    private val _eventFlow = MutableSharedFlow<AuthEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun verifyMemberId() {
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                Log.d("test", "토큰 정보 보기 실패", error)
            } else if (tokenInfo != null) {
                Log.d(
                    "test",
                    "토큰 정보 보기 성공" +
                        "\n회원번호: ${tokenInfo.id}" +
                        "\n만료시간: ${tokenInfo.expiresIn} 초",
                )

                checkNotNull(tokenInfo.id) {
                    viewModelScope.launch {
                        _eventFlow.emit(AuthEvent.Error(IllegalStateException("고유 회원 Id 값이 비었습니다.")))
                    }
                }

                excuteMemberIdVerification(tokenInfo.id ?: -1)
            }
        }
    }

    private fun excuteMemberIdVerification(userId: Long) = viewModelScope.launch {
        verifyMemberIdUseCase(userId).onSuccess { result ->
            if (result) {
                _eventFlow.emit(AuthEvent.VerifySuccess)
                return@launch
            }

            _eventFlow.emit(AuthEvent.VerifyFailed(userId.toString()))
        }.onFailure {
            _eventFlow.emit(AuthEvent.Error(it))
        }
    }

    sealed class AuthEvent {
        data object VerifySuccess : AuthEvent()
        data class Error(val throwable: Throwable) : AuthEvent()
        data class VerifyFailed(val userId: String) : AuthEvent()
    }
}
