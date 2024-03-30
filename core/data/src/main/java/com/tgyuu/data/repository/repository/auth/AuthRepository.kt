package com.tgyuu.data.repository.repository.auth

interface AuthRepository {
    suspend fun verifyMemberId(userId: String): Result<Boolean>
}
