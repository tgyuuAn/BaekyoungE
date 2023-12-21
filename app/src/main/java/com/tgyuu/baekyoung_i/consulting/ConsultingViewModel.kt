package com.tgyuu.baekyoung_i.consulting

import androidx.lifecycle.ViewModel
import com.pknu.domain.usecase.consulting.GetConsultingChattingUseCase
import com.pknu.domain.usecase.consulting.PostConsultingInformationUseCase
import com.pknu.domain.usecase.consulting.PostUserChattingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConsultingViewModel @Inject constructor(
    private val postUserChattingUseCase: PostUserChattingUseCase,
    private val postConsultingInformationUseCase: PostConsultingInformationUseCase,
    private val getConsultingChattingUseCase: GetConsultingChattingUseCase,
) : ViewModel() {

}