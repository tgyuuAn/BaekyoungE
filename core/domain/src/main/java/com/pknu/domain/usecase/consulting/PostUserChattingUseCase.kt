package com.pknu.domain.usecase.consulting

import com.pknu.data.repository.repository.consulting.ConsultingRepository
import javax.inject.Inject

class PostUserChattingUseCase @Inject constructor(
    private val consultingRepository: ConsultingRepository,
) {
    suspend operator fun invoke(chatUser: String) =
        consultingRepository.postUserChatting(chatUser = chatUser)
}