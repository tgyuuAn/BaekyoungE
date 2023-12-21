package com.pknu.domain.usecase.consulting

import com.pknu.data.repository.repository.consulting.ConsultingRepository
import javax.inject.Inject

class PostUserChattingUseCase @Inject constructor(
    private val consultingRepository: ConsultingRepository,
) {
    suspend operator fun invoke(chatAssistant: String, chatUser: String) =
        consultingRepository.postUserChatting(chatAssistant = chatAssistant, chatUser = chatUser)
}