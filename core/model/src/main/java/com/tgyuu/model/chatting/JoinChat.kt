package com.tgyuu.model.chatting

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class JoinChat(
    val mentorId: String,
    val mentorNickName: String,
    val menteeId: String,
    val menteeNickName: String,
    val roomId: String,
    val lastChatting: String,
    val lastSentTime: String,
) {
    fun getFormattedLastSentTime(): String {
        val localUpdatedTime = lastSentTime.parseAsLocalDateTime()

        if (localUpdatedTime.dayOfYear == generateNowDateTime().dayOfYear - 1) {
            return "어제"
        }

        if (localUpdatedTime.dayOfYear < generateNowDateTime().dayOfYear - 1) {
            return "${localUpdatedTime.monthValue}월 ${localUpdatedTime.dayOfMonth}일"
        }

        val formattedHour = formatHour(localUpdatedTime)
        val formattedMinute = localUpdatedTime.minute.toString().padStart(2, '0')
        return "$formattedHour:$formattedMinute"
    }

    private fun formatHour(dateTime: LocalDateTime): String = when {
        dateTime.hour < 12 -> "오전 ${dateTime.hour}"
        dateTime.hour == 12 -> "오후 12"
        else -> "오후 ${dateTime.hour - 12}"
    }

    private fun String.parseAsLocalDateTime(): LocalDateTime =
        LocalDateTime.parse(this, DateTimeFormatter.ISO_LOCAL_DATE_TIME)

    private fun generateNowDateTime(zoneId: ZoneId = ZoneId.of("Asia/Seoul")): LocalDateTime =
        LocalDateTime.now(zoneId)
}
