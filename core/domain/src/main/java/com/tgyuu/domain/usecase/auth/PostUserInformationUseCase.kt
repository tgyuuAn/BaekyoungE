package com.tgyuu.domain.usecase.auth

import com.tgyuu.data.repository.repository.auth.AuthRepository
import javax.inject.Inject

class PostUserInformationUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(
        userId: String,
        nickName: String,
        sex: String,
        major: String,
        grade: Int,
    ): Result<Unit> = authRepository.postUserInformation(
        userId = userId,
        nickName = nickName,
        sex = sex,
        major = major,
        grade = grade,
    )
}
