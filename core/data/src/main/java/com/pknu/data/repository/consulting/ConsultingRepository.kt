package com.pknu.data.repository.consulting

import com.pknu.network.model.consulting.ConsultingResponse
import kotlinx.coroutines.flow.Flow

interface ConsultingRepository {
    suspend fun postConsultingInformation(
        grade: Int,
        major: String,
    ): Result<Unit>

    suspend fun postUserChatting(
        chatAssistant: String,
        chatUser: String,
    ): Result<Unit>

    suspend fun getAssistantChatting(): Flow<Result<ConsultingResponse>>
}