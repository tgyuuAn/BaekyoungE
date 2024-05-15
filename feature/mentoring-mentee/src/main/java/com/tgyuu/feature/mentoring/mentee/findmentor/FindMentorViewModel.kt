package com.tgyuu.feature.mentoring.mentee.findmentor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val _mentorsInfo = MutableStateFlow<List<MentorInfo>?>(null)
    val mentorsInfo = _mentorsInfo.asStateFlow()

    init {
        getAllMentorsInfo()
    }

    fun getAllMentorsInfo() = viewModelScope.launch {
        getAllMentorsInfoUseCase()
            .onSuccess { _mentorsInfo.value = it }
            .onFailure { }
    }
}
