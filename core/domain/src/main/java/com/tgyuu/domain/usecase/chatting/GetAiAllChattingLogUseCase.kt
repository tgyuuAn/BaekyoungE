package com.tgyuu.domain.usecase.chatting

import com.tgyuu.domain.repository.chatting.AiChattingRepository
import com.tgyuu.model.storage.ChattingRoom
import javax.inject.Inject

class GetAiAllChattingLogUseCase @Inject constructor(
    private val aiChattingRepository: AiChattingRepository,
) {
    suspend operator fun invoke(): Result<List<ChattingRoom>> =
        aiChattingRepository.getLocalAllChattingRoom()
}
