package com.tgyuu.domain.usecase.chatting

import com.tgyuu.domain.repository.chatting.LocalChattingRepository
import javax.inject.Inject

class DeleteLocalChattingRoomUseCase @Inject constructor(
    private val localChattingRepository: LocalChattingRepository,
) {
    suspend operator fun invoke(id: String): Result<Unit> = localChattingRepository.deleteLocalChattingRoom(id = id)
}
