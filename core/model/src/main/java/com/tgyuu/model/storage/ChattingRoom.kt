package com.tgyuu.model.storage

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class ChattingRoom(
    val id: String,
    val lastMessage: String,
    val lastUpdated: String,
) {
    fun getFormattedUpdateTime(): String {
        val localUpdatedTime = lastUpdated.parseAsLocalDateTime()
        val formattedHour = formatHour(localUpdatedTime)
        val formattedMinute = localUpdatedTime.minute.toString().padStart(2, '0')
        return "${localUpdatedTime.monthValue}월 ${localUpdatedTime.dayOfMonth}일 $formattedHour:$formattedMinute"
    }

    private fun formatHour(dateTime: LocalDateTime): String = when {
        dateTime.hour < 12 -> "오전 ${dateTime.hour}"
        dateTime.hour == 12 -> "오후 12"
        else -> "오후 ${dateTime.hour - 12}"
    }

    private fun String.parseAsLocalDateTime(): LocalDateTime =
        LocalDateTime.parse(this, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
}

