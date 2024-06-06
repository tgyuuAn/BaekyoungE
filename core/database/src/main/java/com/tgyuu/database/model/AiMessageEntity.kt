package com.tgyuu.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ai_message")
data class AiMessageEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "chatting_room_id")
    val chattingRoomId: String,
    @ColumnInfo(name = "message_from")
    val messageFrom: String,
    @ColumnInfo(name = "message_to")
    val messageTo: String,
    val content: String,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
)
