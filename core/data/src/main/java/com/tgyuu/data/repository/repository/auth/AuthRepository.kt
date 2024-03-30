package com.tgyuu.data.repository.repository.auth

interface AuthRepository {
    suspend fun verifyUserId(userId: String): Result<Boolean>

    suspend fun postUserInformation(
        userId: String,
        nickName: String,
        sex: String,
        major: String,
        grade: Int,
    ): Result<Unit>
}
