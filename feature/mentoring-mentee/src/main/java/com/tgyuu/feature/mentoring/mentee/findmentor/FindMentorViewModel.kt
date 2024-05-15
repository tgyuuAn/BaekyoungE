package com.tgyuu.feature.mentoring.mentee.findmentor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tgyuu.common.util.UiState
import com.tgyuu.domain.usecase.mentoring.GetAllMentorsInfoUseCase
import com.tgyuu.model.mentoring.MentorInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindMentorViewModel @Inject constructor(
    private val getAllMentorsInfoUseCase: GetAllMentorsInfoUseCase,
) : ViewModel() {
    private val _mentorsInfo = MutableStateFlow<UiState<List<MentorInfo>>>(UiState.Loading)
    val mentorsInfo = _mentorsInfo.asStateFlow()

    init {
        getAllMentorsInfo()
    }

    fun getAllMentorsInfo() = viewModelScope.launch {
        _mentorsInfo.value = UiState.Loading

        getAllMentorsInfoUseCase()
            .onSuccess { _mentorsInfo.value = UiState.Success(it) }
            .onFailure { _mentorsInfo.value = UiState.Error(it.message ?: "알 수 없는 요청입니다.")}
    }
}
