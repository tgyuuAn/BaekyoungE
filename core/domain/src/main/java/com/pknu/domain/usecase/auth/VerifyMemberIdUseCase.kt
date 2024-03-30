package com.pknu.domain.usecase.auth

import com.pknu.data.repository.repository.auth.AuthRepository
import javax.inject.Inject

class VerifyMemberIdUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(userId: Int) = authRepository.verifyMemberId(userId.toString())
}
