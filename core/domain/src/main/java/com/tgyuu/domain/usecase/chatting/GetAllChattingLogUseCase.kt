package com.tgyuu.domain.usecase.chatting

import com.tgyuu.data.repository.repository.chatting.ChattingRepository
import com.tgyuu.model.storage.ChattingRoom
import javax.inject.Inject

class GetAllChattingLogUseCase @Inject constructor(
    private val chattingRepository: ChattingRepository,
) {
    suspend operator fun invoke(): Result<List<ChattingRoom>> =
        chattingRepository.getAllChattingRoom()
}
