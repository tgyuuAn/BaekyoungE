package com.pknu.data.repository.consulting

import com.pknu.model.consulting.ConsultingChatting
import com.pknu.network.source.consulting.ConsultingDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConsultingRepositoryImpl @Inject constructor(
    private val consultingDataSource: ConsultingDataSource
) : ConsultingRepository {
    override suspend fun postConsultingInformation(grade: Int, major: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun postUserChatting(chatAssistant: String, chatUser: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getAssistantChatting(): Flow<Result<ConsultingChatting>> {
        TODO("Not yet implemented")
    }
}