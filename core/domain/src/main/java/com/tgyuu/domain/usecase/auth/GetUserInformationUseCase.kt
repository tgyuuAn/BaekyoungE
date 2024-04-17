package com.tgyuu.domain.usecase.auth

import com.tgyuu.data.repository.repository.auth.AuthRepository
import javax.inject.Inject

class GetUserInformationUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(userId: String) = authRepository.getUserInformation(userId)
}
