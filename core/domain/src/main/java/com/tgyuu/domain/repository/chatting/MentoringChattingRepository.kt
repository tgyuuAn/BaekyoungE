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

    suspend fun getMentorChattingRoom(userId: String): Result<List<JoinChat>>

    suspend fun getMenteeChattingRoom(userId: String): Result<List<JoinChat>>
}
