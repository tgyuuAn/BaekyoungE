package com.tgyuu.data.repository.repository.consulting

import com.tgyuu.model.consulting.ConsultingChatting
import kotlinx.coroutines.flow.Flow

interface ConsultingRepository {
    suspend fun postConsultingInformation(
        grade: Int,
        major: String,
    ): Result<Unit>

    suspend fun postUserChatting(
        chatUser: String,
    ): Result<Unit>

    fun getChatting(): Flow<Result<List<ConsultingChatting>>>
}
