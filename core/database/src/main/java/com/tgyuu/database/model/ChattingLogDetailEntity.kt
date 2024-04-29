package com.tgyuu.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "chatting_log_detail",
)
data class ChattingLogDetailEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "chatting_log")
    val chattingLog: List<String>,
    val publishDate: String,
)
