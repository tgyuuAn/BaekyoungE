package com.tgyuu.domain.repository.chatting

import com.tgyuu.model.storage.ChattingRoom
import com.tgyuu.model.storage.Message

interface LocalChattingRepository {
    suspend fun insertLocalMessage(
        id: String,
        chattingRoomId: String,
        messageFrom: String,
        messageTo: String,
        content: String,
        createdAt: String,
    ): Result<Unit>

    suspend fun insertLocalChattingRoom(
        id: String,
        lastChatting: String,
        updatedAt: String,
    ): Result<Unit>

    suspend fun deleteLocalChattingRoom(id: String): Result<Unit>

    suspend fun getLocalChattingRoom(roomId: String): Result<ChattingRoom>

    suspend fun getLocalAllChattingRoomMessages(roomId: String): Result<List<Message>>

    suspend fun getAllChattingRoom(): Result<List<ChattingRoom>>
}
