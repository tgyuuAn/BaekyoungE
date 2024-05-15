package com.tgyuu.data.repository.repository.mentoring

import com.tgyuu.domain.repository.mentoring.MentoringRepository
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
    ): Result<Unit> = mentoringDataSource.postMentorInfo(
        MentorInfoRequest(
            userId = userId,
            nickName = nickName,
            registrationDate = registrationDate,
        ),
    )

    override suspend fun deleteMentorInfo(userId: String): Result<Unit> =
        mentoringDataSource.deleteMentorInfo(userId = userId)

    override suspend fun getMentorInfo(userId: String): Result<MentorInfo> =
        mentoringDataSource.getMentorInfo(userId = userId).mapCatching {
            MentorInfo(
                userId = it.userId,
                nickName = it.nickName,
                registrationDate = it.registrationDate,
            )
        }

    override suspend fun getAllMentors(): Result<List<MentorInfo>> =
        mentoringDataSource.getAllMentorsInfo().mapCatching {
            it.map {
                MentorInfo(
                    userId = it.userId,
                    nickName = it.nickName,
                    registrationDate = it.registrationDate,
                )
            }
        }
}
