package com.tgyuu.domain.usecase.auth

import com.tgyuu.data.repository.repository.auth.AuthRepository
import javax.inject.Inject

class VerifyMemberIdUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(userId: Long): Result<Boolean> =
        authRepository.verifyMemberId(userId.toString())
}
