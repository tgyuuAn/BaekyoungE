package com.tgyuu.network.source.consulting

import com.tgyuu.network.model.consulting.AiChatRequest
import com.tgyuu.network.model.consulting.AiChatResponse

interface AiConsultingDataSource {
    suspend fun postChatMessage(AiChatRequest: AiChatRequest): Result<AiChatResponse>
}
