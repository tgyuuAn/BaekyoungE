package com.tgyuu.domain.usecase.chatting

import com.tgyuu.domain.repository.chatting.LocalChattingRepository
import com.tgyuu.model.storage.ChattingRoom
import javax.inject.Inject

class GetLocalAllChattingLogUseCase @Inject constructor(
    private val localChattingRepository: LocalChattingRepository,
) {
    suspend operator fun invoke(): Result<List<ChattingRoom>> =
        localChattingRepository.getLocalAllChattingRoom()
}
