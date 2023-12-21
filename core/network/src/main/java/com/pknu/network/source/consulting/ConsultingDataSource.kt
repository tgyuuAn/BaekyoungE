package com.pknu.network.source.consulting

import kotlinx.coroutines.flow.Flow

interface ConsultingDataSource {
    suspend fun postConsultingInformation(): Result<Unit>

    suspend fun postUserChatting(): Result<Unit>

    fun getAssistantChatting(): Flow<Result<Unit>>
}