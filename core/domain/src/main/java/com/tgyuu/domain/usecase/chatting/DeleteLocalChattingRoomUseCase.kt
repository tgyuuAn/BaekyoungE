package com.tgyuu.domain.usecase.chatting

import com.tgyuu.domain.repository.chatting.AiChattingRepository
import javax.inject.Inject

class DeleteLocalChattingRoomUseCase @Inject constructor(
    private val aiChattingRepository: AiChattingRepository,
) {
    suspend operator fun invoke(id: String): Result<Unit> = aiChattingRepository.deleteChattingRoom(id = id)
}
