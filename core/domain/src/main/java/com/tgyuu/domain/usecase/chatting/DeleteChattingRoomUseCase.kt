package com.tgyuu.domain.usecase.chatting

import com.tgyuu.data.repository.repository.chatting.ChattingRepository
import javax.inject.Inject

class DeleteChattingRoomUseCase @Inject constructor(
    private val chattingRepository: ChattingRepository,
) {
    suspend operator fun invoke(
        id: String,
        lastChatting: String,
        updatedAt: String,
    ): Result<Unit> = chattingRepository.deleteChattingRoom(
        id = id,
        lastChatting = lastChatting,
        updatedAt = updatedAt,
    )
}
