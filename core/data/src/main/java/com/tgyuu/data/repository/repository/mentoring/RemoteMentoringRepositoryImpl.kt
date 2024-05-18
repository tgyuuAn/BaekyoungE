package com.tgyuu.data.repository.repository.mentoring

import android.util.Log
import com.tgyuu.domain.repository.mentoring.RemoteMentoringRepository
import com.tgyuu.model.mentoring.MentorInfo
import com.tgyuu.network.model.mentoring.MentorInfoRequest
import com.tgyuu.network.source.mentoring.MentoringDataSource
import javax.inject.Inject

class RemoteMentoringRepositoryImpl @Inject constructor(
    private val mentoringDataSource: MentoringDataSource,
    private val localMentoringRepositoryImpl: LocalMentoringRepositoryImpl,
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
        localMentoringRepositoryImpl.postMentorInfo(
            userId = userId,
            nickName = nickName,
            registrationDate = registrationDate,
        )
    }

    override suspend fun deleteMentorInfo(userId: String): Result<Unit> =
        mentoringDataSource.deleteMentorInfo(userId = userId).onSuccess {
            localMentoringRepositoryImpl.deleteMentorInfo(userId = userId)
        }

    override suspend fun getMentorInfo(userId: String): Result<MentorInfo> {
        val localResult = localMentoringRepositoryImpl.getMentorInfo(userId).getOrNull()
        Log.d("test", "localResult : " + localResult.toString())

        return if (localResult != null) {
            Result.success(localResult)
        } else {
            Log.d("test", "원격 호출")

            mentoringDataSource.getMentorInfo(userId).mapCatching {
                MentorInfo(
                    userId = it.userId,
                    nickName = it.nickName,
                    registrationDate = it.registrationDate,
                )
            }.onSuccess {
                localMentoringRepositoryImpl.postMentorInfo(
                    userId = it.userId,
                    nickName = it.nickName,
                    registrationDate = it.registrationDate,
                )
            }
        }
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
