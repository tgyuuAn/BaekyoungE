package com.tgyuu.data.repository.repository.chatting

import com.tgyuu.database.dao.ChattingDao
import com.tgyuu.database.model.ChattingRoomEntity
import com.tgyuu.database.model.MessageEntity
import com.tgyuu.model.storage.ChattingRoom
import com.tgyuu.model.storage.Message
import javax.inject.Inject

class ChattingRepositoryImpl @Inject constructor(
    private val chattingDao: ChattingDao,
) : ChattingRepository {
    override suspend fun insertMessage(
        id: String,
        chattingRoomId: String,
        messageFrom: String,
        messageTo: String,
        content: String,
        createdAt: String,
    ) = chattingDao.insertMessage(
        MessageEntity(
            id,
            chattingRoomId,
            messageFrom,
            messageTo,
            content,
            createdAt,
        ),
    )

    override suspend fun insertChattingRoom(id: String, lastChatting: String, createdAt: String) =
        chattingDao.insertChattingRoom(
            ChattingRoomEntity(
                id,
                lastChatting,
                createdAt,
            ),
        )

    override suspend fun getAllChattingRoomMessages(roomId: String): List<Message> =
        chattingDao.getAllChattingRoomMessages(roomId).map {
            Message(
                id = it.id,
                chattingRoomId = it.chattingRoomId,
                messageFrom = it.messageFrom,
                messageTo = it.messageTo,
                content = it.content,
                createdAt = it.createdAt,
            )
        }

    override suspend fun getAllChattingRoom(): List<ChattingRoom> =
        chattingDao.getAllChattingRoom().map {
            ChattingRoom(
                id = it.id,
                lastChatting = it.lastChatting,
                createdAt = it.createdAt,
            )
        }
}
