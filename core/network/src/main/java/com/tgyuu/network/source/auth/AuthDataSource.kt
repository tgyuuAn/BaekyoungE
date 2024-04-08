package com.tgyuu.network.source.auth

import com.tgyuu.network.model.auth.UserInformationRequest

interface AuthDataSource {
    suspend fun verifyUserId(userId: String): Result<Boolean>

    suspend fun postUserInformation(userInformationRequest: UserInformationRequest): Result<Unit>
}
