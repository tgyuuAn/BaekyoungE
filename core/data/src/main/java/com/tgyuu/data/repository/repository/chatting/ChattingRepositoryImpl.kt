package com.tgyuu.data.repository.repository.chatting

import com.tgyuu.database.dao.ChattingDao
import com.tgyuu.database.model.ChattingRoomEntity
import com.tgyuu.database.model.MessageEntity
import javax.inject.Inject

class ChattingRepositoryImpl @Inject constructor(
    private val chattingDao: ChattingDao,
) : ChattingRepository {
    override suspend fun insertMessage(message: MessageEntity) = chattingDao.insertMessage(message)

    override suspend fun insertChattingRoom(chattingRoom: ChattingRoomEntity) =
        chattingDao.insertChattingRoom(chattingRoom)

    override suspend fun getAllChattingRoomMessages(roomId: String): List<MessageEntity>
    = chattingDao.getAllChattingRoomMessages(roomId)

    override suspend fun getAllChattingRoom(): List<ChattingRoomEntity>
    = chattingDao.getAllChattingRoom()
}
