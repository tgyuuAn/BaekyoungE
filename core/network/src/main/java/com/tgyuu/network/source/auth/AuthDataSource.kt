package com.tgyuu.network.source.auth

import com.tgyuu.model.auth.UserInformation
import com.tgyuu.network.model.auth.UserInformationRequest
import com.tgyuu.network.model.auth.UserInformationResponse

interface AuthDataSource {
    suspend fun verifyUserId(userId: String): Result<Boolean>

    suspend fun getUserInformation(userId: String): Result<UserInformationResponse>

    suspend fun postUserInformation(userInformationRequest: UserInformationRequest): Result<Unit>
}
