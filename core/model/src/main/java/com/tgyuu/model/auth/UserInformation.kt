package com.tgyuu.model.auth

import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class UserInformation(
    val userId: String = "",
    val nickName: String = "",
    val gender: String = "",
    val major: String = "",
    val grade: Int = -1,
    val fcmToken: String = "",
    val registrationDate: String = "",
) {
    fun calculateTimeSinceRegistration(): Duration {
        val registrationDateTime = registrationDate.toISOLocalDateTime()
        val nowDateTime = generateNowDateTime()
        return Duration.between(registrationDateTime, nowDateTime)
    }

    private fun String.toISOLocalDateTime(): LocalDateTime = LocalDateTime.parse(
        this,
        DateTimeFormatter.ISO_LOCAL_DATE_TIME,
    )

    private fun generateNowDateTime(zoneId: ZoneId = ZoneId.of("Asia/Seoul")): LocalDateTime =
        LocalDateTime.now(zoneId)
}
