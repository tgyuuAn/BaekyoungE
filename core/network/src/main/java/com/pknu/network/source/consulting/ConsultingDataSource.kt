package com.pknu.network.source.consulting

import com.pknu.network.model.consulting.ChatLogResponse
import com.pknu.network.model.consulting.ChatRequest
import com.pknu.network.model.consulting.ConsultingRequest
import kotlinx.coroutines.flow.Flow

interface ConsultingDataSource {
    suspend fun postConsultingInformation(consultingRequest: ConsultingRequest): Result<Unit>

    suspend fun postUserChatting(chatRequest: ChatRequest): Result<Unit>

    fun getChattingLog(): Flow<Result<List<ChatLogResponse>>>
}