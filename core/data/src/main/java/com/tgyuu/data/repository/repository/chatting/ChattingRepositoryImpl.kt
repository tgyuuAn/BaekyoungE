package com.tgyuu.data.repository.repository.chatting

import android.util.Log
import com.tgyuu.data.util.generateNowDateTime
import com.tgyuu.data.util.toISOLocalDateTimeString
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

    override suspend fun insertChattingRoom(id: String, lastChatting: String, updatedAt: String) =
        chattingDao.insertChattingRoom(
            ChattingRoomEntity(
                id,
                lastChatting,
                updatedAt,
            ),
        )

    override suspend fun getChattingRoom(roomId: String): Result<ChattingRoom> = runCatching {
        val chattingRoomEntity = chattingDao.getChattingRoom(roomId)
            ?: ChattingRoomEntity(
                id = generateNowDateTime().toISOLocalDateTimeString(),
                lastMessage = "",
                lastUpdated = "",
            )

        Log.d("test", chattingRoomEntity.toString())

        ChattingRoom(
            id = chattingRoomEntity.id,
            lastMessage = chattingRoomEntity.lastMessage,
            lastUpdated = chattingRoomEntity.lastUpdated,
        )
    }

    override suspend fun getAllChattingRoomMessages(roomId: String): Result<List<Message>> =
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
