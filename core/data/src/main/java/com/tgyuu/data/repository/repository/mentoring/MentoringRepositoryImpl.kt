package com.tgyuu.data.repository.repository.mentoring

import android.util.Log
import com.tgyuu.database.dao.MentorDao
import com.tgyuu.database.model.MentorEntity
import com.tgyuu.domain.repository.mentoring.RemoteMentoringRepository
import com.tgyuu.model.mentoring.MentorInfo
import com.tgyuu.network.model.mentoring.MentorInfoRequest
import com.tgyuu.network.source.mentoring.MentoringDataSource
import javax.inject.Inject

class MentoringRepositoryImpl @Inject constructor(
    private val mentoringDataSource: MentoringDataSource,
    private val mentorDao: MentorDao,
) : RemoteMentoringRepository {
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
    ).onSuccess {
        mentorDao.insertMentorInformation(
            MentorEntity(
                userId = userId,
                nickName = nickName,
                registrationDate = registrationDate,
            ),
        )
    }

    override suspend fun deleteMentorInfo(userId: String): Result<Unit> =
        mentoringDataSource.deleteMentorInfo(userId = userId).onSuccess {
            mentorDao.deleteMentorInformation(userId = userId)
        }

    override suspend fun getMentorInfo(userId: String): Result<MentorInfo> {
        val mentorEntity = mentorDao.getMentorInformation()

        return if (mentorEntity != null) {
            Result.success(
                MentorInfo(
                    userId = mentorEntity.userId,
                    nickName = mentorEntity.nickName,
                    registrationDate = mentorEntity.registrationDate,
                ),
            )
        } else {
            mentoringDataSource.getMentorInfo(userId).mapCatching {
                MentorInfo(
                    userId = it.userId,
                    nickName = it.nickName,
                    registrationDate = it.registrationDate,
                )
            }.onSuccess {
                mentorDao.insertMentorInformation(
                    MentorEntity(
                        userId = it.userId,
                        nickName = it.nickName,
                        registrationDate = it.registrationDate,
                    ),
                )
            }
        }
    }

    override suspend fun getAllMentors(userId: String): Result<List<MentorInfo>> =
        mentoringDataSource.getAllMentorsInfo().mapCatching {
            it.filter { it.userId != userId }
                .map {
                    MentorInfo(
                        userId = it.userId,
                        nickName = it.nickName,
                        registrationDate = it.registrationDate,
                    )
                }
        }
}
