package com.pknu.data.repository.repository.consulting

import com.pknu.model.consulting.ConsultingChatting
import com.pknu.network.model.consulting.ChatRequest
import com.pknu.network.model.consulting.ConsultingRequest
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

    override suspend fun postUserChatting(chatUser: String): Result<Unit> =
        consultingDataSource.postUserChatting(
            ChatRequest(
                chatUser = chatUser
            )
        )

    override fun getChatting(): Flow<Result<List<ConsultingChatting>>> =
        consultingDataSource.getChattingLog().map { response ->
            response.mapCatching { chatLogResponseList ->
                chatLogResponseList.map {
                    it.toConsultingChatting()
                }
            }
        }
}