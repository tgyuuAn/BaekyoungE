package com.tgyuu.domain.usecase.auth

import com.tgyuu.domain.repository.auth.AuthRepository
import com.tgyuu.model.auth.UserInformation
import javax.inject.Inject

class GetUserInformationUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(userId: String): Result<UserInformation> =
        authRepository.getUserInformation(userId)
}
