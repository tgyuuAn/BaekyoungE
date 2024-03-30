package com.pknu.network.source.auth

interface AuthDataSource {
    suspend fun verifyMemberId(userId: String): Result<Boolean>
}
