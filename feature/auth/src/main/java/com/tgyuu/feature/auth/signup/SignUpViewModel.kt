package com.tgyuu.feature.auth.signup

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tgyuu.common.util.getFCMToken
import com.tgyuu.domain.usecase.auth.PostUserInformationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val postUserInformationUseCase: PostUserInformationUseCase,
) : ViewModel() {
    private val _signUpEventFlow = MutableSharedFlow<SignUpEvent>()
    val signUpEventFlow = _signUpEventFlow.asSharedFlow()

    private val _userId = MutableStateFlow("")

    private val _nickname = MutableStateFlow("")
    val nickname = _nickname.asStateFlow()

    private val _gender = MutableStateFlow(Gender.NONE)
    val gender = _gender.asStateFlow()

    private val _major = MutableStateFlow("")
    val major = _major.asStateFlow()

    private val _grade = MutableStateFlow("1")
    val grade = _grade.asStateFlow()

    private val _isSignUpSuccess = MutableStateFlow(false)
    val isSignUpSuccess = _isSignUpSuccess.asStateFlow()

    fun signUp() = viewModelScope.launch {
        if (_nickname.value.isEmpty()) {
            _signUpEventFlow.emit(SignUpEvent.SignUpFailed("별명을 입력 해주세요."))
            return@launch
        }

        if (_major.value.isEmpty()) {
            _signUpEventFlow.emit(SignUpEvent.SignUpFailed("학과를 입력 해주세요."))
            return@launch
        }

        if (_grade.value.isEmpty()) {
            _signUpEventFlow.emit(SignUpEvent.SignUpFailed("학년을 입력 해주세요."))
            return@launch
        }

        if (_gender.value == Gender.NONE) {
            _signUpEventFlow.emit(SignUpEvent.SignUpFailed("성별을 선택 해주세요."))
            return@launch
        }

        postUserInformationUseCase(
            userId = _userId.value,
            nickName = _nickname.value,
            gender = _gender.value.displayName,
            major = _major.value,
            grade = _grade.value.toInt(),
            fcmToken = getFCMToken(),
        ).onSuccess {
            _isSignUpSuccess.value = true
            delay(4500L)
            _signUpEventFlow.emit(SignUpEvent.SignUpSuccess)
        }.onFailure {
            _signUpEventFlow.emit(SignUpEvent.SignUpFailed("회원정보 등록에 실패하였습니다."))
        }
    }

    fun setUserId(userId: String) {
        _userId.value = userId
    }

    fun setNickname(nickname: String) {
        _nickname.value = nickname
    }

    fun setMajor(major: String) {
        _major.value = major
    }

    fun setGender(gender: Gender) {
        _gender.value = gender
    }

    fun setGrade(grade: String) {
        if (grade.isDigitsOnly()) {
            _grade.value = grade
        }
    }

    sealed class SignUpEvent {
        data object SignUpSuccess : SignUpEvent()
        data class SignUpFailed(val message: String) : SignUpEvent()
    }

    enum class Gender(val displayName: String) {
        NONE("성별을 선택해주세요."), MALE("남자"), FEMALE("여자")
    }
}
