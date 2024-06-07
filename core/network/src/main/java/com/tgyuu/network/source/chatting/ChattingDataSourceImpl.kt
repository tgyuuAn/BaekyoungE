package com.tgyuu.network.source.chatting

import android.util.Log
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObject
import com.tgyuu.network.constant.JOIN_CHAT_COLLECTION
import com.tgyuu.network.constant.MESSAGE_COLLECTION
import com.tgyuu.network.di.OpenAiApi
import com.tgyuu.network.model.auth.UserInformationResponse
import com.tgyuu.network.model.chatting.ai.AiChatRequest
import com.tgyuu.network.model.chatting.ai.AiChatResponse
import com.tgyuu.network.model.chatting.mentoring.JoinChatRequest
import com.tgyuu.network.model.chatting.mentoring.JoinChatResponse
import com.tgyuu.network.model.chatting.mentoring.MentoringChatRequest
import com.tgyuu.network.model.chatting.mentoring.MentoringChatResponse
import com.tgyuu.network.util.await
import com.tgyuu.network.util.generateNowDateTime
import com.tgyuu.network.util.logAnalytics
import com.tgyuu.network.util.toISOLocalDateTimeString
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class ChattingDataSourceImpl @Inject constructor(
    private val openAiApi: OpenAiApi,
    private val firebaseFirestore: FirebaseFirestore,
) : ChattingDataSource {
    override suspend fun postAiMessage(aiChatRequest: AiChatRequest): Result<AiChatResponse> =
        openAiApi.postChatMessage(aiChatRequest).await()

    override suspend fun postMentoringMessage(
        mentoringChatRequest: MentoringChatRequest,
        joinChatRequest: JoinChatRequest,
    ): Result<Unit> =
        runCatching {
            firebaseFirestore.collection(MESSAGE_COLLECTION)
                .document()
                .set(mentoringChatRequest)
                .await()

            firebaseFirestore.collection(JOIN_CHAT_COLLECTION)
                .document(joinChatRequest.roomId)
                .set(joinChatRequest)
                .await()
        }

    override suspend fun subscribeMessages(roomId: String): Flow<MentoringChatResponse> =
        callbackFlow {
            val listenerRegistration = firebaseFirestore.collection(MESSAGE_COLLECTION)
                .whereEqualTo("roomId", roomId)
                .whereGreaterThan("createdAt", generateNowDateTime().toISOLocalDateTimeString())
                .orderBy("createdAt", Query.Direction.ASCENDING)
                .addSnapshotListener { value, error ->
                    Log.d("test", "subscribeMessage 작동")

                    if (error != null) {
                        logAnalytics(error.stackTraceToString())
                        return@addSnapshotListener
                    }

                    for (dc in value!!.documentChanges) {
                        when (dc.type) {
                            DocumentChange.Type.ADDED -> trySend(dc.document.toObject<MentoringChatResponse>())
                            DocumentChange.Type.MODIFIED -> Log.d(
                                "test",
                                "Modified message: ${dc.document.data}",
                            )

                            DocumentChange.Type.REMOVED -> Log.d(
                                "test",
                                "Removed message: ${dc.document.data}",
                            )
                        }
                    }
                }

            awaitClose { listenerRegistration.remove() }
        }

    override suspend fun getPreviousMessages(
        roomId: String,
        lastTime: String,
    ): Result<List<MentoringChatResponse>> =
        runCatching {
            Log.d("test", "PreviousMessage 호출")

            val documents = firebaseFirestore.collection(MESSAGE_COLLECTION)
                .whereEqualTo("roomId", roomId)
                .whereLessThan("createdAt", lastTime)
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .limit(PAGING_SIZE)
                .get()
                .await()

            documents.map { document ->
                checkNotNull(document.toObject<MentoringChatResponse>())
            }.reversed()
        }

    override suspend fun getMentorChattingRoom(userId: String): Result<List<JoinChatResponse>> =
        runCatching {
            firebaseFirestore.collection(JOIN_CHAT_COLLECTION)
                .whereEqualTo("mentorId", userId)
                .get()
                .await()
                .documents
                .map { document ->
                    document.toObject<JoinChatResponse>()
                        ?: throw IllegalArgumentException("Invalid mentor info")
                }
        }

    override suspend fun getMenteeChattingRoom(userId: String): Result<List<JoinChatResponse>> =
        runCatching {
            firebaseFirestore.collection(JOIN_CHAT_COLLECTION)
                .whereEqualTo("menteeId", userId)
                .get()
                .await()
                .documents
                .map { document ->
                    document.toObject<JoinChatResponse>()
                        ?: throw IllegalArgumentException("Invalid mentee info")
                }
        }


    companion object{
        private const val PAGING_SIZE = 30L
    }
}
