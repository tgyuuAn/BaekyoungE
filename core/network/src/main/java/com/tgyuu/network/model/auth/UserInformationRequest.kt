package com.tgyuu.network.model.auth

data class UserInformationRequest(
    val userId: String = "",
    val nickName: String = "",
    val sex: String = "",
    val major: String = "",
    val grade: Int = -1,
)
