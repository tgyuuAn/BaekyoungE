package com.tgyuu.data.repository.repository.auth

import com.tgyuu.data.util.generateNowDateTime
import com.tgyuu.data.util.toISOLocalDateTimeString
import com.tgyuu.database.dao.UserDao
import com.tgyuu.database.model.UserEntity
import com.tgyuu.domain.repository.auth.AuthRepository
import com.tgyuu.model.auth.UserInformation
import com.tgyuu.network.model.auth.UserInformationRequest
import com.tgyuu.network.source.auth.AuthDataSource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val userDao: UserDao,
) : AuthRepository {
    override suspend fun verifyUserId(userId: String): Result<Boolean> =
        authDataSource.verifyUserId(userId)

    override suspend fun getUserInformation(userId: String): Result<UserInformation> =
        runCatching {
            userDao.getUserInformation()?.let {
                UserInformation(
                    userId = it.id,
                    nickName = it.nickName,
                    gender = it.gender,
                    major = it.major,
                    grade = it.grade,
                    registrationDate = it.registrationDate,
                )
            } ?: authDataSource.getUserInformation(userId).map {
                UserInformation(
                    userId = it.userId,
                    nickName = it.nickName,
                    gender = it.gender,
                    major = it.major,
                    grade = it.grade,
                    registrationDate = it.registrationDate,
                )
            }.onSuccess {
                userDao.insertUserInformation(
                    UserEntity(
                        id = it.userId,
                        nickName = it.nickName,
                        gender = it.gender,
                        major = it.major,
                        grade = it.grade,
                        registrationDate = it.registrationDate,
                    ),
                )
            }.getOrThrow()
        }

    override suspend fun postUserInformation(
        userId: String,
        nickName: String,
        gender: String,
        major: String,
        grade: Int,
    ): Result<Unit> = authDataSource.postUserInformation(
        UserInformationRequest(
            userId = userId,
            nickName = nickName,
            gender = gender,
            major = major,
            grade = grade,
            registrationDate = generateNowDateTime().toISOLocalDateTimeString(),
        ),
    ).onSuccess {
        userDao.insertUserInformation(
            UserEntity(
                id = userId,
                nickName = nickName,
                gender = gender,
                major = major,
                grade = grade,
                registrationDate = generateNowDateTime().toISOLocalDateTimeString(),
            ),
        )
    }

    override suspend fun updateUserInformation(
        userId: String,
        nickName: String,
        gender: String,
        major: String,
        grade: Int,
        registrationDate: String,
    ): Result<Unit> = authDataSource.postUserInformation(
        UserInformationRequest(
            userId = userId,
            nickName = nickName,
            gender = gender,
            major = major,
            grade = grade,
            registrationDate = registrationDate,
        ),
    ).onSuccess {
        userDao.insertUserInformation(
            UserEntity(
                id = userId,
                nickName = nickName,
                gender = gender,
                major = major,
                grade = grade,
                registrationDate = registrationDate,
            ),
        )
    }

    override suspend fun deleteUserInformation(userId: String): Result<Unit> =
        authDataSource.deleteUserInformation(userId).onSuccess {
            userDao.deleteUserInformation(userId)
        }
}
