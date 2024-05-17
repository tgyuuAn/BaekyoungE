package com.tgyuu.domain.usecase.chatting

import com.tgyuu.domain.repository.chatting.LocalChattingRepository
import com.tgyuu.model.storage.Message
import javax.inject.Inject

class GetAllChattingRoomMessagesUseCase @Inject constructor(
    private val localChattingRepository: LocalChattingRepository,
) {
    suspend operator fun invoke(roomId: String): Result<List<Message>> =
        localChattingRepository.getLocalAllChattingRoomMessages(roomId)
}
