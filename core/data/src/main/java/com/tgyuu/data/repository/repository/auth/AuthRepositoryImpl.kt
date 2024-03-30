package com.tgyuu.data.repository.repository.auth

import com.tgyuu.network.source.auth.AuthDataSource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
) : AuthRepository {
    override suspend fun verifyMemberId(userId: String): Result<Boolean> =
        authDataSource.verifyMemberId(userId)
}
