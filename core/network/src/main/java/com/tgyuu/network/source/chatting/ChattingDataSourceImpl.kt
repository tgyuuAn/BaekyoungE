package com.tgyuu.network.source.chatting

import android.util.Log
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.tgyuu.network.constant.JOIN_CHAT_COLLECTION
import com.tgyuu.network.constant.MESSAGE_COLLECTION
import com.tgyuu.network.di.OpenAiApi
import com.tgyuu.network.model.chatting.ai.AiChatRequest
import com.tgyuu.network.model.chatting.ai.AiChatResponse
import com.tgyuu.network.model.chatting.mentoring.JoinChatRequest
import com.tgyuu.network.model.chatting.mentoring.JoinChatResponse
import com.tgyuu.network.model.chatting.mentoring.MentoringChatRequest
import com.tgyuu.network.model.chatting.mentoring.MentoringChatResponse
import com.tgyuu.network.util.await
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

    override suspend fun getAllMessage(roomId: String): Flow<MentoringChatResponse> = callbackFlow {
        val listenerRegistration = firebaseFirestore.collection(MESSAGE_COLLECTION)
            .whereEqualTo("roomId", roomId)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.d("test", "listen:error", error)
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

    override suspend fun getRemoteAllChattingRoom(userId: String): Result<List<JoinChatResponse>> =
        runCatching {
            firebaseFirestore.collection(JOIN_CHAT_COLLECTION)
                .whereArrayContains("userId", userId)
                .get()
                .await()
                .documents
                .map { document ->
                    document.toObject<JoinChatResponse>()
                        ?: throw IllegalArgumentException("Invalid mentor info")
                }
        }
}
