package com.tgyuu.network.source.auth

import com.tgyuu.network.model.auth.UserInformationRequest
import com.tgyuu.network.model.auth.UserInformationResponse

interface AuthDataSource {
    suspend fun verifyUserId(userId: String): Result<Boolean>

    suspend fun getUserInformation(userId: String): Result<UserInformationResponse>

    suspend fun deleteUserInformation(userId: String): Result<Unit>

    suspend fun postUserInformation(userInformationRequest: UserInformationRequest): Result<Unit>
}
