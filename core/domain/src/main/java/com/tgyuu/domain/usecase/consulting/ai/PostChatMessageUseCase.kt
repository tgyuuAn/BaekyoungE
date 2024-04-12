package com.tgyuu.domain.usecase.consulting.ai

import android.util.Log
import com.tgyuu.data.repository.repository.consulting.ConsultingRepository
import com.tgyuu.model.consulting.ChatLog
import javax.inject.Inject

class PostChatMessageUseCase @Inject constructor(
    private val consultingRepository: ConsultingRepository,
) {
    suspend operator fun invoke(chatLog: ChatLog): Result<ChatLog> {
        Log.d("test", chatLog.toString())

        return consultingRepository.postChatMessage(chatLog)
    }
}