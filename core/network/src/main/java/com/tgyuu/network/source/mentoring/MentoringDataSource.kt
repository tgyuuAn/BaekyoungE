package com.tgyuu.network.source.mentoring

import com.tgyuu.network.model.mentoring.MentorInfoRequest
import com.tgyuu.network.model.mentoring.MentorInfoResponse

interface MentoringDataSource {
    suspend fun postMentorInfo(mentorInfoRequest: MentorInfoRequest): Result<Unit>

    suspend fun deleteMentorInfo(userId: String): Result<Unit>

    suspend fun getMentorInfo(userId: String): Result<MentorInfoResponse>

    suspend fun getAllMentorsInfo(): Result<List<MentorInfoResponse>>
}
