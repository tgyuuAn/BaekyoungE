package com.tgyuu.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chatting_room")
data class ChattingRoomEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "last_message")
    val lastMessage: String,
    @ColumnInfo(name = "last_updated")
    val lastUpdated: String,
)
