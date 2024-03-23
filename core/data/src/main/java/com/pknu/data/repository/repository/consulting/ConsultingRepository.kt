package com.pknu.data.repository.repository.consulting

import com.pknu.model.consulting.ConsultingChatting
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
