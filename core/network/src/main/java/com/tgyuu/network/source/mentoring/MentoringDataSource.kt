package com.tgyuu.network.source.mentoring

import com.tgyuu.network.model.mentoring.MentorInfoRequest
import com.tgyuu.network.model.mentoring.MentorInfoResponse

interface MentoringDataSource {
    suspend fun postMentor(mentorInfoRequest: MentorInfoRequest): Result<Unit>

    suspend fun getAllMentors(): Result<List<MentorInfoResponse>>
}
