package com.tgyuu.network.source.consulting

import com.tgyuu.network.model.consulting.ChatLogResponse
import com.tgyuu.network.model.consulting.ChatRequest
import com.tgyuu.network.model.consulting.ConsultingRequest
import kotlinx.coroutines.flow.Flow

interface ConsultingDataSource {
    suspend fun postConsultingInformation(consultingRequest: ConsultingRequest): Result<Unit>

    suspend fun postUserChatting(chatRequest: ChatRequest): Result<Unit>

    fun getChattingLog(): Flow<Result<List<ChatLogResponse>>>
}
