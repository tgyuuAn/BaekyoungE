package com.pknu.network.source.consulting

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.pknu.network.constant.CHATTING_LOG_COLLECTION
import com.pknu.network.constant.CHAT_USER_COLLECTION
import com.pknu.network.constant.USER_INFORMATION_COLLECTION
import com.pknu.network.model.consulting.ChatLogResponse
import com.pknu.network.model.consulting.ChatRequest
import com.pknu.network.model.consulting.ConsultingRequest
import com.pknu.network.model.consulting.ConsultingResponse
import com.pknu.network.util.await
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ConsultingDataSourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
) : ConsultingDataSource {

    override suspend fun postConsultingInformation(consultingRequest: ConsultingRequest): Result<Unit> =
        runCatching {
            firebaseFirestore.collection(USER_INFORMATION_COLLECTION)
                .document("8I9y4HW94HGLiTlxPCFf")
                .set(consultingRequest)
                .await()
        }

    override suspend fun postUserChatting(chatRequest: ChatRequest): Result<Unit> = runCatching {
        firebaseFirestore.collection(CHAT_USER_COLLECTION)
            .document("s0tSqorXJqEn4ntIxcQj")
            .set(chatRequest)
            .await()
    }

    override fun getChattingLog(): Flow<Result<List<ChatLogResponse>>> = flow {
        while (true) {
            val consulting = runCatching {
                val task = firebaseFirestore.collection(CHATTING_LOG_COLLECTION)
                    .document("lqDCZs2rMJo6wReAU29M")
                    .get()
                    .await()

                val consultingResponse = checkNotNull(task.toObject<ConsultingResponse>())
                consultingResponse.chat_log
            }
            emit(consulting)
            delay(100L)
        }
    }
}
