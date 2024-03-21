package com.tgyuu.baekyoung_i.auth.signup

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    private val _nickname = MutableStateFlow("")
    val nickname = _nickname.asStateFlow()

    private val _major = MutableStateFlow("")
    val major = _major.asStateFlow()

    private val _grade = MutableStateFlow("")
    val grade = _grade.asStateFlow()
}