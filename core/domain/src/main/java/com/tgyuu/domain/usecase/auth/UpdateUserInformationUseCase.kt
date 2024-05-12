package com.tgyuu.domain.usecase.auth

import com.tgyuu.domain.repository.auth.AuthRepository
import javax.inject.Inject

class UpdateUserInformationUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(
        userId: String,
        nickName: String,
        gender: String,
        major: String,
        grade: Int,
        registrationDate: String,
    ): Result<Unit> = authRepository.updateUserInformation(
        userId = userId,
        nickName = nickName,
        gender = gender,
        major = major,
        grade = grade,
        registrationDate = registrationDate,
    )
}
