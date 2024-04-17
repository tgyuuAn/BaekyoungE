package com.tgyuu.network.source.auth

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.tgyuu.network.constant.USER_INFORMATION_COLLECTION
import com.tgyuu.network.model.auth.UserInformationRequest
import com.tgyuu.network.model.auth.UserInformationResponse
import com.tgyuu.network.util.await
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
) : AuthDataSource {
    override suspend fun verifyUserId(userId: String): Result<Boolean> = runCatching {
        val document = firebaseFirestore.collection(USER_INFORMATION_COLLECTION)
            .document(userId)
            .get()
            .await()

        // 데이터가 없을 경우 False를 반환, 있을경우 True를 반환
        document.toObject<UserInformationResponse>() != null
    }

    override suspend fun getUserInformation(userId: String): Result<UserInformationResponse> = runCatching {
        val document = firebaseFirestore.collection(USER_INFORMATION_COLLECTION)
            .document(userId)
            .get()
            .await()

        checkNotNull(document.toObject<UserInformationResponse>())
    }

    override suspend fun postUserInformation(userInformationRequest: UserInformationRequest):
        Result<Unit> = runCatching {
        firebaseFirestore.collection(USER_INFORMATION_COLLECTION)
            .document(userInformationRequest.userId)
            .set(userInformationRequest)
            .await()
    }
}
