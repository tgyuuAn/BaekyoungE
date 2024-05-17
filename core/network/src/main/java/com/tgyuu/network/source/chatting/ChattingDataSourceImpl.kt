package com.tgyuu.network.source.chatting

import android.util.Log
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.tgyuu.network.constant.CHATTING_ROOM_COLLECTION
import com.tgyuu.network.di.OpenAiApi
import com.tgyuu.network.model.auth.UserInformationResponse
import com.tgyuu.network.model.chatting.ai.AiChatRequest
import com.tgyuu.network.model.chatting.ai.AiChatResponse
import com.tgyuu.network.model.chatting.mentoring.MentoringChatRequest
import com.tgyuu.network.model.chatting.mentoring.MentoringChatResponse
import com.tgyuu.network.util.await
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChattingDataSourceImpl @Inject constructor(
    private val openAiApi: OpenAiApi,
    private val firebaseFirestore: FirebaseFirestore,
) : ChattingDataSource {
    override suspend fun postAiMessage(AiChatRequest: AiChatRequest): Result<AiChatResponse> =
        openAiApi.postChatMessage(AiChatRequest).await()

    override suspend fun postMentoringMessage(mentoringChatRequest: MentoringChatRequest): Result<Unit> =
        runCatching {
            firebaseFirestore.collection(CHATTING_ROOM_COLLECTION)
                .document(CHATTING_ROOM_COLLECTION)
                .collection(mentoringChatRequest.roomId)
                .document(mentoringChatRequest.createdAt + "-" + mentoringChatRequest.userId)
                .set(mentoringChatRequest)
                .await()
        }

    override suspend fun getAllMessage(roomId: String): Flow<MentoringChatResponse> = callbackFlow {
        val listenerRegistration = firebaseFirestore.collection(CHATTING_ROOM_COLLECTION)
            .document(CHATTING_ROOM_COLLECTION)
            .collection(roomId)
            .addSnapshotListener { value, error ->
                if (error != null){
                    Log.d("test", "listen:error", error)
                    return@addSnapshotListener
                }

                for (dc in value!!.documentChanges){
                    when(dc.type){
                        DocumentChange.Type.ADDED -> trySend(dc.document.toObject<MentoringChatResponse>())
                        DocumentChange.Type.MODIFIED -> Log.d("test", "Modified message: ${dc.document.data}")
                        DocumentChange.Type.REMOVED -> Log.d("test", "Removed message: ${dc.document.data}")
                    }
                }
            }

        awaitClose { listenerRegistration.remove() }
    }
}
