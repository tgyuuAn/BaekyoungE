package com.tgyuu.baekyoung_i.consulting.chatting

import androidx.lifecycle.ViewModel
import com.pknu.domain.usecase.consulting.GetConsultingChattingUseCase
import com.pknu.domain.usecase.consulting.PostUserChattingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChattingViewModel @Inject constructor(
    private val postUserChattingUseCase: PostUserChattingUseCase,
    private val getConsultingChattingUseCase: GetConsultingChattingUseCase,
) : ViewModel() {

}