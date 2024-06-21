package com.tgyuu.feature.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.user.UserApiClient
import com.tgyuu.common.util.getFCMToken
import com.tgyuu.domain.usecase.auth.GetUserInformationUseCase
import com.tgyuu.domain.usecase.auth.UpdateUserInformationUseCase
import com.tgyuu.domain.usecase.auth.VerifyMemberIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val verifyMemberIdUseCase: VerifyMemberIdUseCase,
    private val updateUserInformationUseCase: UpdateUserInformationUseCase,
    private val getUserInformationUseCase: GetUserInformationUseCase,
) : ViewModel() {
    private val _eventFlow = MutableSharedFlow<AuthEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun verifyMemberId() = UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
        viewModelScope.launch {
            if (error != null) {
                _eventFlow.emit(AuthEvent.Error(IllegalStateException("토큰 정보 보기 실패")))
                return@launch
            }

            if (tokenInfo == null) {
                _eventFlow.emit(AuthEvent.Error(IllegalStateException("토큰 정보가 비었습니다.")))
                return@launch
            }

            Log.d(
                "test",
                "토큰 정보 보기 성공" +
                    "\n회원번호: ${tokenInfo.id}" +
                    "\n만료시간: ${tokenInfo.expiresIn} 초",
            )

            if (tokenInfo.id == null) {
                _eventFlow.emit(AuthEvent.Error(IllegalStateException("고유 회원 Id 값이 비었습니다.")))
            }

            excuteMemberIdVerification(tokenInfo.id.toString())
        }
    }

    private suspend fun excuteMemberIdVerification(userId: String) =
        verifyMemberIdUseCase(userId).onSuccess { result ->
            if (!result) {
                _eventFlow.emit(AuthEvent.VerifyFailed(userId))
                return@onSuccess
            }

            getUserInformationUseCase(userId).onSuccess {
                updateUserInformationUseCase(
                    userId = it.userId,
                    nickName = it.nickName,
                    gender = it.gender,
                    major = it.major,
                    grade = it.grade,
                    registrationDate = it.registrationDate,
                    fcmToken = getFCMToken(),
                )
                _eventFlow.emit(AuthEvent.VerifySuccess)
            }
        }.onFailure { _eventFlow.emit(AuthEvent.Error(it)) }
}

sealed class AuthEvent {
    data object VerifySuccess : AuthEvent()
    data class Error(val throwable: Throwable) : AuthEvent()
    data class VerifyFailed(val userId: String) : AuthEvent()
}
