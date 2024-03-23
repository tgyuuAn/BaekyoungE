package com.tgyuu.baekyounge.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    private val _signUpEventFlow = MutableSharedFlow<SignUpEvent>()
    val signUpEventFlow = _signUpEventFlow.asSharedFlow()

    private val _nickname = MutableStateFlow("")
    val nickname = _nickname.asStateFlow()

    private val _major = MutableStateFlow("")
    val major = _major.asStateFlow()

    private val _grade = MutableStateFlow("")
    val grade = _grade.asStateFlow()

    private val _isSignUpSuccess = MutableStateFlow(false)
    val isSignUpSuccess = _isSignUpSuccess.asStateFlow()

    fun signUp() = viewModelScope.launch {
        _isSignUpSuccess.value = true
        delay(4000L)
        _signUpEventFlow.emit(SignUpEvent.SignUpSuccess)
        // Todo
    }

    fun setNickname(nickname: String) {
        _nickname.value = nickname
    }

    fun setMajor(major: String) {
        _major.value = major
    }

    fun setGrade(grade: String) {
        _grade.value = grade
    }

    sealed class SignUpEvent {
        data object SignUpSuccess : SignUpEvent()
    }
}
