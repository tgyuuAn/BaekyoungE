package com.pknu.data.repository.repository.consulting

import com.pknu.model.consulting.ConsultingChatting
import com.pknu.network.model.consulting.ConsultingRequest
import com.pknu.network.model.consulting.ConsultingResponse
import com.pknu.network.source.consulting.ConsultingDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ConsultingRepositoryImpl @Inject constructor(
    private val consultingDataSource: ConsultingDataSource,
) : ConsultingRepository {
    override suspend fun postConsultingInformation(grade: Int, major: String): Result<Unit> =
        consultingDataSource.postConsultingInformation(
            ConsultingRequest(
                grade = grade,
                major = major
            )
        )

    override suspend fun postUserChatting(chatAssistant: String, chatUser: String): Result<Unit> =
        consultingDataSource.postUserChatting(
            ConsultingResponse(
                chat_assistant = chatAssistant,
                chat_user = chatUser,
            )
        )

    override suspend fun getAssistantChatting(): Flow<Result<ConsultingChatting>> =
        consultingDataSource.getAssistantChatting().map { response ->
            response.mapCatching {
                it.toConsultingChatting()
            }
        }
}