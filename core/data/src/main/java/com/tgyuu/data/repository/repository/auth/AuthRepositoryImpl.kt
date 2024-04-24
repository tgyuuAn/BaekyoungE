package com.tgyuu.data.repository.repository.auth

import com.tgyuu.data.util.generateNowDateTime
import com.tgyuu.data.util.toISOLocalDateTimeString
import com.tgyuu.model.auth.UserInformation
import com.tgyuu.network.model.auth.UserInformationRequest
import com.tgyuu.network.source.auth.AuthDataSource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
) : AuthRepository {
    override suspend fun verifyUserId(userId: String): Result<Boolean> =
        authDataSource.verifyUserId(userId)

    override suspend fun getUserInformation(userId: String): Result<UserInformation> =
        authDataSource.getUserInformation(userId).mapCatching {
            UserInformation(
                userId = it.userId,
                nickName = it.nickName,
                gender = it.gender,
                major = it.major,
                grade = it.grade,
                registrationDate = it.registrationDate,
            )
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
    )

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
    )
}
