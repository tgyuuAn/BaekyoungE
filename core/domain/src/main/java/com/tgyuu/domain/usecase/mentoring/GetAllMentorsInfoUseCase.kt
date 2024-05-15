package com.tgyuu.domain.usecase.mentoring

import com.tgyuu.domain.repository.mentoring.MentoringRepository
import com.tgyuu.model.mentoring.MentorInfo
import javax.inject.Inject

class GetAllMentorsInfoUseCase @Inject constructor(
    private val mentoringRepository: MentoringRepository,
) {
    suspend operator fun invoke(): Result<List<MentorInfo>> = mentoringRepository.getAllMentors()
}
