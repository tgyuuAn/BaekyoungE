package com.tgyuu.domain.usecase.auth

import com.tgyuu.domain.repository.auth.AuthRepository
import javax.inject.Inject

class DeleteUserInformationUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(userId: String) = authRepository.deleteUserInformation(userId)
}
