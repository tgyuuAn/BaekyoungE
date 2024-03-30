package com.tgyuu.domain.usecase.consulting

import com.tgyuu.data.repository.repository.consulting.ConsultingRepository
import javax.inject.Inject

class PostConsultingInformationUseCase @Inject constructor(
    private val consultingRepository: ConsultingRepository,
) {
    suspend operator fun invoke(grade: Int, major: String): Result<Unit> =
        consultingRepository.postConsultingInformation(grade = grade, major = major)
}
