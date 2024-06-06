package com.tgyuu.feature.mentoring.mentee.findmentor

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tgyuu.common.util.UiState
import com.tgyuu.domain.usecase.auth.GetUserInformationUseCase
import com.tgyuu.domain.usecase.mentoring.GetAllMentorsInfoUseCase
import com.tgyuu.model.auth.UserInformation
import com.tgyuu.model.mentoring.MentorInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindMentorViewModel @Inject constructor(
    private val getUserInformationUseCase: GetUserInformationUseCase,
    private val getAllMentorsInfoUseCase: GetAllMentorsInfoUseCase,
) : ViewModel() {
    private val _userInformation = MutableStateFlow(UserInformation())
    val userInformation = _userInformation.asStateFlow()

    private val _mentorsInfo = MutableStateFlow<UiState<List<MentorInfo>>>(UiState.Loading)
    val mentorsInfo = _mentorsInfo.asStateFlow()

    init {
        getUserInformation(-1)
    }

    fun getUserInformation(userId: Long) = viewModelScope.launch {
        getUserInformationUseCase(userId.toString())
            .onSuccess {
                _userInformation.value = it
                getAllMentorsInfo()
            }
            .onFailure { }
    }

    fun getAllMentorsInfo() = viewModelScope.launch {
        _mentorsInfo.value = UiState.Loading

        getAllMentorsInfoUseCase(_userInformation.value.userId)
            .onSuccess { _mentorsInfo.value = UiState.Success(it) }
            .onFailure { _mentorsInfo.value = UiState.Error(it.message ?: "알 수 없는 요청입니다.") }
    }
}
