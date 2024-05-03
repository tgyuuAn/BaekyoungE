package com.tgyuu.domain.usecase.chatting

import com.tgyuu.data.repository.repository.chatting.ChattingRepository
import com.tgyuu.model.storage.Message
import javax.inject.Inject

class GetAllChattingRoomMessagesUseCase @Inject constructor(
    private val chattingRepository: ChattingRepository,
) {
    suspend operator fun invoke(roomId: String): Result<List<Message>> =
        chattingRepository.getAllChattingRoomMessages(roomId)
}
