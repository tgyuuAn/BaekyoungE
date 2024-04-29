package com.tgyuu.data.repository.repository.chatting

import com.tgyuu.database.model.ChattingRoomEntity
import com.tgyuu.database.model.MessageEntity

interface ChattingRepository {
    suspend fun insertMessage(message: MessageEntity)

    suspend fun insertChattingRoom(chattingRoom: ChattingRoomEntity)

    suspend fun getAllChattingRoomMessages(roomId: String): List<MessageEntity>

    suspend fun getAllChattingRoom(): List<ChattingRoomEntity>
}
