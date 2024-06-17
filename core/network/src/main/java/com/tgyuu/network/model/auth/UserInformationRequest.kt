package com.tgyuu.network.model.auth

data class UserInformationRequest(
    val userId: String,
    val nickName: String,
    val gender: String,
    val major: String,
    val grade: Int,
    val fcmToken: String,
    val registrationDate: String,
)
