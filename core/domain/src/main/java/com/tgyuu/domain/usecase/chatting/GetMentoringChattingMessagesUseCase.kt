package com.tgyuu.domain.usecase.chatting

import com.tgyuu.domain.repository.chatting.MentoringChattingRepository
import com.tgyuu.model.chatting.MentoringMessage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMentoringChattingMessagesUseCase @Inject constructor(
    private val mentoringChattingRepository: MentoringChattingRepository,
) {
    suspend operator fun invoke(roomId: String): Flow<MentoringMessage> =
        mentoringChattingRepository.getAllMessage(roomId)
}
