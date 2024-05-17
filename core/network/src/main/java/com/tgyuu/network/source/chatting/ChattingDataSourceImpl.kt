package com.tgyuu.network.source.chatting

import com.google.firebase.firestore.FirebaseFirestore
import com.tgyuu.network.constant.CHATTING_ROOM_COLLECTION
import com.tgyuu.network.di.OpenAiApi
import com.tgyuu.network.model.chatting.ai.AiChatRequest
import com.tgyuu.network.model.chatting.ai.AiChatResponse
import com.tgyuu.network.model.chatting.mentoring.MentoringChatRequest
import com.tgyuu.network.util.await
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
}
