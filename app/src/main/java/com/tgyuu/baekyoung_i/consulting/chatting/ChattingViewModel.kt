package com.tgyuu.baekyoung_i.consulting.chatting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pknu.domain.usecase.consulting.GetConsultingChattingUseCase
import com.pknu.domain.usecase.consulting.PostUserChattingUseCase
import com.pknu.model.consulting.ConsultingChatting
import com.tgyuu.common.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ChattingViewModel @Inject constructor(
    private val postUserChattingUseCase: PostUserChattingUseCase,
    private val getConsultingChattingUseCase: GetConsultingChattingUseCase,
) : ViewModel() {

    val chat: StateFlow<UiState<List<ConsultingChatting>>> =
        getConsultingChattingUseCase()
            .map { result ->
                result.fold(
                    onSuccess = { UiState.Success(it) },
                    onFailure = { UiState.Error(it.message ?: "알 수 없는 오류입니다.") }
                )
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = UiState.Loading
            )

    private val _userInput: MutableStateFlow<String> = MutableStateFlow("")
    val userInput get() = _userInput.asStateFlow()

    fun setUserInput(input: String) {
        _userInput.value = input
    }
}