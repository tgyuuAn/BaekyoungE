package com.tgyuu.domain.repository.chatting

import com.tgyuu.model.chatting.AiMessage
import com.tgyuu.model.chatting.AiMessages

interface RemoteChattingRepository {
    suspend fun postAiMessage(chatLog: List<AiMessage>): Result<AiMessages>

    suspend fun postMentoringChatMessage(
        roomId: String,
        messageId: String,
        userId: String,
        content: String,
        createdAt: String,
    ): Result<Unit>
}
