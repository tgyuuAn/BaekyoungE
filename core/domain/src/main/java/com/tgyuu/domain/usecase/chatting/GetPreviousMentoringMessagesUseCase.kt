package com.tgyuu.domain.usecase.chatting

import com.tgyuu.domain.repository.chatting.MentoringChattingRepository
import com.tgyuu.model.chatting.MentoringMessage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPreviousMentoringMessagesUseCase @Inject constructor(
    private val mentoringChattingRepository: MentoringChattingRepository,
) {
    suspend operator fun invoke(roomId: String, lastTime: String): Result<List<MentoringMessage>> =
        mentoringChattingRepository.getPreviousMessages(roomId, lastTime)
}
