package com.tgyuu.domain.repository.chatting

import com.tgyuu.model.chatting.AiMessage
import com.tgyuu.model.chatting.AiMessages
import com.tgyuu.model.storage.ChattingRoom
import com.tgyuu.model.storage.Message

interface AiChattingRepository {
    suspend fun postAiMessage(chatLog: List<AiMessage>): Result<AiMessages>

    suspend fun insertMessage(
        id: String,
        chattingRoomId: String,
        messageFrom: String,
        messageTo: String,
        content: String,
        createdAt: String,
    ): Result<Unit>

    suspend fun insertChattingRoom(
        id: String,
        lastChatting: String,
        updatedAt: String,
    ): Result<Unit>

    suspend fun deleteChattingRoom(id: String): Result<Unit>

    suspend fun getChattingRoom(roomId: String): Result<ChattingRoom>

    suspend fun getAllChattingRoomMessages(roomId: String): Result<List<Message>>

    suspend fun getLocalAllChattingRoom(): Result<List<ChattingRoom>>
}
