package com.tgyuu.data.repository.repository.chatting

import com.tgyuu.database.model.ChattingRoomEntity
import com.tgyuu.database.model.MessageEntity

interface ChattingRepository {
    suspend fun insertMessage(
        id: String,
        chattingRoomId: String,
        messageFrom: String,
        messageTo: String,
        content: String,
        createdAt: String,
    )
    suspend fun insertChattingRoom(
        id: String,
        lastChatting: String,
        createdAt: String,
    )

    suspend fun getAllChattingRoomMessages(roomId: String): List<MessageEntity>

    suspend fun getAllChattingRoom(): List<ChattingRoomEntity>
}
