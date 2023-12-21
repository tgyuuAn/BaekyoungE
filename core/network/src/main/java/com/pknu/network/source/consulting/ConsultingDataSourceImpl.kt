package com.pknu.network.source.consulting

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConsultingDataSourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
) : ConsultingDataSource {
    override suspend fun postConsultingInformation(): Result<Unit> = runCatching {
        firebaseFirestore.collection()
    }

    override suspend fun postUserChatting(): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun getAssistantChatting(): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }
}