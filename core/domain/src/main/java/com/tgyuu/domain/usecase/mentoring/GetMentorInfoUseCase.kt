package com.tgyuu.domain.usecase.mentoring

import com.tgyuu.domain.repository.mentoring.RemoteMentoringRepository
import javax.inject.Inject

class GetMentorInfoUseCase @Inject constructor(
    private val remoteMentoringRepository: RemoteMentoringRepository,
) {
    suspend operator fun invoke(userId: String) =
        remoteMentoringRepository.getMentorInfo(userId = userId)
}
