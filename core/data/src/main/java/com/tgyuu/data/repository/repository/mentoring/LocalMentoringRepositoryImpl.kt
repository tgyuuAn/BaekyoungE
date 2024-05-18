package com.tgyuu.data.repository.repository.mentoring

import com.tgyuu.database.dao.MentorDao
import com.tgyuu.database.model.MentorEntity
import com.tgyuu.domain.repository.mentoring.LocalMentoringRepository
import com.tgyuu.model.mentoring.MentorInfo
import javax.inject.Inject

class LocalMentoringRepositoryImpl @Inject constructor(
    private val mentorDao: MentorDao,
) : LocalMentoringRepository {
    override suspend fun postMentorInfo(
        userId: String,
        nickName: String,
        registrationDate: String,
    ): Result<Unit> = runCatching {
        mentorDao.insertMentorInformation(
            MentorEntity(
                userId = userId,
                nickName = nickName,
                registrationDate = registrationDate,
            ),
        )
    }

    override suspend fun deleteMentorInfo(userId: String): Result<Unit> = runCatching {
        mentorDao.deleteMentorInformation(userId)
    }

    override suspend fun getMentorInfo(userId: String): Result<MentorInfo?> = runCatching {
        val mentorEntity = mentorDao.getMentorInformation()

        mentorEntity?.let {
            MentorInfo(
                userId = it.userId,
                nickName = it.nickName,
                registrationDate = it.registrationDate,
            )
        }
    }
}
