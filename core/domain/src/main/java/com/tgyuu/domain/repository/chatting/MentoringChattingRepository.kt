package com.tgyuu.domain.repository.chatting

import com.tgyuu.model.chatting.JoinChat
import com.tgyuu.model.chatting.MentoringMessage
import kotlinx.coroutines.flow.Flow

interface MentoringChattingRepository {
    suspend fun postMentoringChatMessage(
        roomId: String,
        userId: String,
        content: String,
        createdAt: String,
    ): Result<Unit>

    suspend fun getAllMessage(roomId: String): Flow<MentoringMessage>

    suspend fun getAllMentorChattingRoom(userId: String): Result<List<JoinChat>>

    suspend fun getAllMenteeChattingRoom(userId: String): Result<List<JoinChat>>
}
