package com.tgyuu.domain.usecase.mentoring

import com.tgyuu.domain.repository.mentoring.MentoringRepository
import javax.inject.Inject

class DeleteMentorInfoUseCase @Inject constructor(
    private val mentoringRepository: MentoringRepository,
) {
    suspend operator fun invoke(userId: String) =
        mentoringRepository.deleteMentorInfo(userId = userId)
}
