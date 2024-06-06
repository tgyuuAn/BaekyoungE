package com.tgyuu.network.source.chatting

import com.google.firebase.Timestamp
import com.tgyuu.network.model.chatting.ai.AiChatRequest
import com.tgyuu.network.model.chatting.ai.AiChatResponse
import com.tgyuu.network.model.chatting.mentoring.JoinChatRequest
import com.tgyuu.network.model.chatting.mentoring.JoinChatResponse
import com.tgyuu.network.model.chatting.mentoring.MentoringChatRequest
import com.tgyuu.network.model.chatting.mentoring.MentoringChatResponse
import kotlinx.coroutines.flow.Flow

interface ChattingDataSource {
    suspend fun postAiMessage(aiChatRequest: AiChatRequest): Result<AiChatResponse>

    suspend fun postMentoringMessage(
        mentoringChatRequest: MentoringChatRequest,
        joinChatRequest: JoinChatRequest,
    ): Result<Unit>

    suspend fun subscribeMessages(roomId: String): Flow<MentoringChatResponse>

    suspend fun getPreviousMessages(roomId: String, lastTime: String)

    suspend fun getMentorChattingRoom(userId: String): Result<List<JoinChatResponse>>

    suspend fun getMenteeChattingRoom(userId: String): Result<List<JoinChatResponse>>
}
