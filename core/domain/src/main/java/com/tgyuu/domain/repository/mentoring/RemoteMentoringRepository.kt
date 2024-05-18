package com.tgyuu.domain.repository.mentoring

import com.tgyuu.model.mentoring.MentorInfo

interface RemoteMentoringRepository {
    suspend fun postMentorInfo(
        userId: String,
        nickName: String,
        registrationDate: String,
    ): Result<Unit>

    suspend fun deleteMentorInfo(
        userId: String,
    ): Result<Unit>

    suspend fun getMentorInfo(
        userId: String,
    ): Result<MentorInfo>

    suspend fun getAllMentors(): Result<List<MentorInfo>>
}
