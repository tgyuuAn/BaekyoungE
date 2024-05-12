package com.tgyuu.domain.repository.mentoring

import com.tgyuu.model.mentoring.MentorInfo

interface MentoringRepository {
    suspend fun postMentorInfo(
        userId: String,
        nickName: String,
        registrationDate: String,
    ): Result<Unit>

    suspend fun getAllMentors(): Result<List<MentorInfo>>
}
