package com.tgyuu.domain.usecase.chatting

import com.tgyuu.domain.repository.chatting.RemoteChattingRepository
import javax.inject.Inject

class PostMentoringMessageUseCase @Inject constructor(
    private val remoteChattingRepository: RemoteChattingRepository,
) {
    suspend operator fun invoke(
        roomId: String,
        userId: String,
      content: String,
    ): Result<Unit> = remoteChattingRepository.postMentoringChatMessage(
        roomId = roomId,
        userId = userId,
        content = content,
        createdAt = generateNowDateTime().toISOLocalDateTimeString(),
    )
}
