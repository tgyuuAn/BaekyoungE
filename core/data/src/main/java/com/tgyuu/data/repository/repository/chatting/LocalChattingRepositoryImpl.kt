package com.tgyuu.data.repository.repository.chatting

import com.tgyuu.data.util.generateNowDateTime
import com.tgyuu.data.util.toISOLocalDateTimeString
import com.tgyuu.database.dao.ChattingDao
import com.tgyuu.database.model.ChattingRoomEntity
import com.tgyuu.database.model.MessageEntity
import com.tgyuu.domain.repository.chatting.LocalChattingRepository
import com.tgyuu.model.storage.ChattingRoom
import com.tgyuu.model.storage.Message
import javax.inject.Inject

class LocalChattingRepositoryImpl @Inject constructor(
    private val chattingDao: ChattingDao,
) : LocalChattingRepository {
    override suspend fun insertLocalMessage(
        id: String,
        chattingRoomId: String,
        messageFrom: String,
        messageTo: String,
        content: String,
        createdAt: String,
    ) = runCatching {
        chattingDao.insertMessage(
            MessageEntity(
                id,
                chattingRoomId,
                messageFrom,
                messageTo,
                content,
                createdAt,
            ),
        )
    }

    override suspend fun insertLocalChattingRoom(id: String, lastChatting: String, updatedAt: String) =
        runCatching {
            chattingDao.insertChattingRoom(
                ChattingRoomEntity(
                    id,
                    lastChatting,
                    updatedAt,
                ),
            )
        }

    override suspend fun deleteLocalChattingRoom(id: String) = runCatching {
        chattingDao.deleteChattingRoom(roomId = id)
        chattingDao.deleteAllChattingRoomMessages(roomId = id)
    }

    override suspend fun getLocalChattingRoom(roomId: String): Result<ChattingRoom> = runCatching {
        val chattingRoomEntity = chattingDao.getChattingRoom(roomId)
            ?: ChattingRoomEntity(
                id = generateNowDateTime().toISOLocalDateTimeString(),
                lastMessage = "",
                lastUpdated = "",
            )

        ChattingRoom(
            id = chattingRoomEntity.id,
            lastMessage = chattingRoomEntity.lastMessage,
            lastUpdated = chattingRoomEntity.lastUpdated,
        )
    }

    override suspend fun getLocalAllChattingRoomMessages(roomId: String): Result<List<Message>> =
        runCatching {
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
        }

    override suspend fun getAllChattingRoom(): Result<List<ChattingRoom>> = runCatching {
        chattingDao.getAllChattingRoom().map {
            ChattingRoom(
                id = it.id,
                lastMessage = it.lastMessage,
                lastUpdated = it.lastUpdated,
            )
        }
    }
}
