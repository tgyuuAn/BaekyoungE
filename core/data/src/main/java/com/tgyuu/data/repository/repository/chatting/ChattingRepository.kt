package com.tgyuu.data.repository.repository.chatting

import com.tgyuu.model.storage.ChattingRoom
import com.tgyuu.model.storage.Message

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

    suspend fun getAllChattingRoomMessages(roomId: String): List<Message>

    suspend fun getAllChattingRoom(): List<ChattingRoom>
}
