package com.tgyuu.domain.usecase.consulting

import com.tgyuu.data.repository.repository.consulting.ConsultingRepository
import javax.inject.Inject

class PostUserChattingUseCase @Inject constructor(
    private val consultingRepository: ConsultingRepository,
) {
    suspend operator fun invoke(
        userId: String,
        chat: String,
    ) = consultingRepository.postUserChatting(
        userId = userId,
        chat = chat,
    )
}
