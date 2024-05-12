package com.tgyuu.domain.usecase.mentoring

import com.tgyuu.domain.repository.mentoring.MentoringRepository
import javax.inject.Inject

class PostMentorUseCase @Inject constructor(
    private val mentoringRepository: MentoringRepository,
) {
    suspend operator fun invoke(
        userId: String,
        nickName: String,
        registrationDate: String,
    ) = mentoringRepository.postMentorInfo(
        userId = userId,
        nickName = nickName,
        registrationDate = registrationDate,
    )
}
