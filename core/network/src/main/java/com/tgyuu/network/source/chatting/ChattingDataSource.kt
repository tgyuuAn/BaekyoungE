package com.tgyuu.network.source.chatting

import com.tgyuu.network.model.chatting.ai.AiChatRequest
import com.tgyuu.network.model.chatting.ai.AiChatResponse
import com.tgyuu.network.model.chatting.mentoring.MentoringChatRequest

interface ChattingDataSource {
    suspend fun postAiMessage(AiChatRequest: AiChatRequest): Result<AiChatResponse>

    suspend fun postMentoringMessage(mentoringChatRequest: MentoringChatRequest): Result<Unit>
}
