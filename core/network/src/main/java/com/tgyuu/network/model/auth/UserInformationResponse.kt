package com.tgyuu.network.model.auth

data class UserInformationResponse(
    val userId: String = "",
    val nickName: String = "",
    val sex: String = "",
    val major: String = "",
    val grade: Int = -1,
    val registrationDate: String = "",
)
