package com.tgyuu.domain.usecase.mentoring

import com.tgyuu.domain.repository.mentoring.RemoteMentoringRepository
import javax.inject.Inject

class PostMentorInfoUseCase @Inject constructor(
    private val remoteMentoringRepository: RemoteMentoringRepository,
) {
    suspend operator fun invoke(
        userId: String,
        nickName: String,
        registrationDate: String,
    ) = remoteMentoringRepository.postMentorInfo(
        userId = userId,
        nickName = nickName,
        registrationDate = registrationDate,
    )
}
