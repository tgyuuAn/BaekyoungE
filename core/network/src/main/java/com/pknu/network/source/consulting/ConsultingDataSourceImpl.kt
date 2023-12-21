package com.pknu.network.source.consulting

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.pknu.network.constant.CHATTING_COLLECTION
import com.pknu.network.constant.USER_INFORMATION_COLLECTION
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

    override suspend fun postConsultingInformation(consultingRequest: ConsultingRequest) =
        runCatching {
            val task = firebaseFirestore.collection(USER_INFORMATION_COLLECTION)
                .document("8I9y4HW94HGLiTlxPCFf")
                .set(consultingRequest)
                .await()
        }

    override suspend fun postUserChatting(consultingResponse: ConsultingResponse) =
        runCatching {
            val task = firebaseFirestore.collection(CHATTING_COLLECTION)
                .document("K2PrpK1UBiJU6ZXUsQh1")
                .set(consultingResponse)
                .await()
        }

    override suspend fun getAssistantChatting(): Flow<Result<ConsultingResponse>> = flow {
        while (true) {
            val consulting = runCatching {
                val task = firebaseFirestore.collection(CHATTING_COLLECTION)
                    .document("K2PrpK1UBiJU6ZXUsQh1")
                    .get()
                    .await()

                checkNotNull(task.toObject<ConsultingResponse>())
            }
            emit(consulting)
            delay(500L)
        }
    }
}