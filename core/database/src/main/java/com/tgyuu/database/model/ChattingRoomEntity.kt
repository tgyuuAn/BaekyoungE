package com.tgyuu.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chatting_room")
data class ChattingRoomEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "last_chatting")
    val lastChatting: String,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
)
