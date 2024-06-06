package com.tgyuu.domain.usecase.mentoring

import com.tgyuu.domain.repository.mentoring.RemoteMentoringRepository
import javax.inject.Inject

class DeleteMentorInfoUseCase @Inject constructor(
    private val remoteMentoringRepository: RemoteMentoringRepository,
) {
    suspend operator fun invoke(userId: String) =
        remoteMentoringRepository.deleteMentorInfo(userId = userId)
}
