package com.tgyuu.domain.usecase.mentoring

import com.tgyuu.domain.repository.mentoring.MentoringRepository
import javax.inject.Inject

class GetAllMentorsUseCase @Inject constructor(
    private val mentoringRepository: MentoringRepository,
) {
    suspend operator fun invoke() = mentoringRepository.getAllMentors()
}
