package com.tgyuu.network.model.auth

import java.time.LocalDateTime

data class UserInformationRequest(
    val userId: String,
    val nickName: String,
    val sex: String,
    val major: String,
    val grade: Int,
    val registrationDate: LocalDateTime,
)
