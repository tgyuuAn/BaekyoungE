package com.tgyuu.domain.usecase.chatting

import com.tgyuu.domain.repository.chatting.AiChattingRepository
import com.tgyuu.model.storage.Message
import javax.inject.Inject

class GetAiAllChattingRoomMessagesUseCase @Inject constructor(
    private val aiChattingRepository: AiChattingRepository,
) {
    suspend operator fun invoke(roomId: String): Result<List<Message>> =
        aiChattingRepository.getAllChattingRoomMessages(roomId)
}
