package com.tgyuu.model.auth

data class UserInformation(
    val userId: String = "",
    val nickName: String = "",
    val gender: String = "",
    val major: String = "",
    val grade: Int = -1,
    val registrationDate: String = "",
)
