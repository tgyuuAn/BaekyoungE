package com.tgyuu.domain.usecase.chatting

import com.tgyuu.domain.repository.chatting.MentoringChattingRepository
import javax.inject.Inject

class PostMentoringMessageUseCase @Inject constructor(
    private val mentoringChattingRepository: MentoringChattingRepository,
) {
    suspend operator fun invoke(
        roomId: String,
        userId: String,
        content: String,
    ): Result<Unit> = mentoringChattingRepository.postMentoringChatMessage(
        roomId = roomId,
        userId = userId,
        content = content,
        createdAt = generateNowDateTime().toISOLocalDateTimeString(),
    )
}
