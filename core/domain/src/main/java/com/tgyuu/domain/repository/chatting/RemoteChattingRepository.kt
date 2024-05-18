package com.tgyuu.domain.repository.chatting

import com.tgyuu.model.chatting.AiMessage
import com.tgyuu.model.chatting.AiMessages
import com.tgyuu.model.chatting.JoinChat
import com.tgyuu.model.chatting.MentoringMessage
import kotlinx.coroutines.flow.Flow

interface RemoteChattingRepository {
    suspend fun postAiMessage(chatLog: List<AiMessage>): Result<AiMessages>

    suspend fun postMentoringChatMessage(
        roomId: String,
        userId: String,
        content: String,
        createdAt: String,
    ): Result<Unit>

    suspend fun getAllMessage(roomId: String): Flow<MentoringMessage>

    suspend fun getRemoteAllChattingRoom(userId: String): Result<List<JoinChat>>
}
