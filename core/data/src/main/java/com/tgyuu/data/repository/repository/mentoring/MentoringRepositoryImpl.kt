package com.tgyuu.data.repository.repository.mentoring

import com.tgyuu.model.mentoring.MentorInfo
import com.tgyuu.network.model.mentoring.MentorInfoRequest
import com.tgyuu.network.source.mentoring.MentoringDataSource
import javax.inject.Inject

class MentoringRepositoryImpl @Inject constructor(
    private val mentoringDataSource: MentoringDataSource,
) : MentoringRepository {
    override suspend fun postMentorInfo(
        userId: String,
        nickName: String,
        registrationDate: String,
    ): Result<Unit> = mentoringDataSource.postMentor(
        MentorInfoRequest(
            userId = userId,
            nickName = nickName,
            registrationDate = registrationDate,
        ),
    )

    override suspend fun getAllMentors(): Result<List<MentorInfo>> =
        mentoringDataSource.getAllMentors().mapCatching {
            it.map {
                MentorInfo(
                    userId = it.userId,
                    nickName = it.nickName,
                    registrationDate = it.registrationDate,
                )
            }
        }
}
