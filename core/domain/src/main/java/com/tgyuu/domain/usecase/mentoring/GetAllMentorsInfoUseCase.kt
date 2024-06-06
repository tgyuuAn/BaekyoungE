package com.tgyuu.domain.usecase.mentoring

import com.tgyuu.domain.repository.mentoring.RemoteMentoringRepository
import com.tgyuu.model.mentoring.MentorInfo
import javax.inject.Inject

class GetAllMentorsInfoUseCase @Inject constructor(
    private val remoteMentoringRepository: RemoteMentoringRepository,
) {
    suspend operator fun invoke(userId: String): Result<List<MentorInfo>> =
        remoteMentoringRepository.getAllMentors(userId)
}
