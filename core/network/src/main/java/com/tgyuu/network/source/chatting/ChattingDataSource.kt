package com.tgyuu.network.source.chatting

import com.tgyuu.network.model.chatting.ai.AiChatRequest
import com.tgyuu.network.model.chatting.ai.AiChatResponse
import com.tgyuu.network.model.chatting.mentoring.MentoringChatRequest
import com.tgyuu.network.model.chatting.mentoring.MentoringChatResponse
import kotlinx.coroutines.flow.Flow

interface ChattingDataSource {
    suspend fun postAiMessage(AiChatRequest: AiChatRequest): Result<AiChatResponse>

    suspend fun postMentoringMessage(mentoringChatRequest: MentoringChatRequest): Result<Unit>

    suspend fun getAllMessage(roomId: String): Flow<MentoringChatResponse>
}
