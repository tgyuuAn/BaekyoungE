package com.tgyuu.domain.usecase.auth

import com.tgyuu.domain.repository.auth.AuthRepository
import javax.inject.Inject

class VerifyMemberIdUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(userId: String): Result<Boolean> =
        authRepository.verifyUserId(userId)
}
