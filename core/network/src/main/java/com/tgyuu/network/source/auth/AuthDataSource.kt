package com.tgyuu.network.source.auth

import com.tgyuu.network.model.auth.UserInformationRequest

interface AuthDataSource {
    suspend fun verifyMemberId(userId: String): Result<Boolean>

    suspend fun postMemberInformation(userInformationRequest: UserInformationRequest): Result<Unit>
}
