package com.tgyuu.domain.usecase.chatting

import com.tgyuu.domain.repository.chatting.ChattingRepository
import javax.inject.Inject

class DeleteChattingRoomUseCase @Inject constructor(
    private val chattingRepository: ChattingRepository,
) {
    suspend operator fun invoke(id: String): Result<Unit> = chattingRepository.deleteChattingRoom(id = id)
}
