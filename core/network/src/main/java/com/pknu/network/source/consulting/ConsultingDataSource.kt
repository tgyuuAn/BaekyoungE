package com.pknu.network.source.consulting

import com.pknu.network.model.consulting.ConsultingRequest
import com.pknu.network.model.consulting.ConsultingResponse
import kotlinx.coroutines.flow.Flow

interface ConsultingDataSource {
    suspend fun postConsultingInformation(consultingRequest: ConsultingRequest): Result<Unit>

    suspend fun postUserChatting(consultingResponse: ConsultingResponse): Result<Unit>

    suspend fun getAssistantChatting(): Flow<Result<ConsultingResponse>>
}