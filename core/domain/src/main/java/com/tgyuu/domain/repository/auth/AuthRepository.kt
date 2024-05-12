package com.tgyuu.domain.repository.auth

import com.tgyuu.model.auth.UserInformation

interface AuthRepository {
    suspend fun verifyUserId(userId: String): Result<Boolean>

    suspend fun getUserInformation(userId: String): Result<UserInformation>

    suspend fun postUserInformation(
        userId: String,
        nickName: String,
        gender: String,
        major: String,
        grade: Int,
    ): Result<Unit>

    suspend fun updateUserInformation(
        userId: String,
        nickName: String,
        gender: String,
        major: String,
        grade: Int,
        registrationDate: String,
    ): Result<Unit>

    suspend fun deleteUserInformation(userId: String): Result<Unit>
}
