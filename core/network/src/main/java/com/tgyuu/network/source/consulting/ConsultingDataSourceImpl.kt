package com.tgyuu.network.source.consulting

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.tgyuu.network.constant.CHAT_LOG_COLLECTION
import com.tgyuu.network.constant.USERS_COLLECTION
import com.tgyuu.network.constant.USER_INFORMATION_COLLECTION
import com.tgyuu.network.model.consulting.ChatLogResponse
import com.tgyuu.network.model.consulting.ChatRequest
import com.tgyuu.network.model.consulting.ConsultingRequest
import com.tgyuu.network.model.consulting.ConsultingResponse
import com.tgyuu.network.util.await
import com.tgyuu.network.util.generateNowDateTime
import com.tgyuu.network.util.toISOLocalDateTimeString
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
        firebaseFirestore.collection(USERS_COLLECTION)
            .document(chatRequest.userId)
            .collection(generateNowDateTime().toISOLocalDateTimeString())
            .document()
            .set(chatRequest)
            .await()
    }

    override fun getChattingLog(): Flow<Result<List<ChatLogResponse>>> = flow {
        while (true) {
            val consulting = runCatching {
                val task = firebaseFirestore.collection(CHAT_LOG_COLLECTION)
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
