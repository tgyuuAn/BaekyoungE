package com.tgyuu.data.repository.repository.chatting

import com.tgyuu.data.util.generateNowDateTime
import com.tgyuu.data.util.toISOLocalDateTimeString
import com.tgyuu.database.dao.AiChattingDao
import com.tgyuu.database.model.AiMessageEntity
import com.tgyuu.database.model.ChattingRoomEntity
import com.tgyuu.domain.repository.chatting.AiChattingRepository
import com.tgyuu.model.chatting.AiMessage
import com.tgyuu.model.chatting.AiMessages
import com.tgyuu.model.chatting.ChattingRole
import com.tgyuu.model.storage.ChattingRoom
import com.tgyuu.model.storage.Message
import com.tgyuu.network.model.chatting.ai.AiChatRequest
import com.tgyuu.network.model.chatting.ai.MessageDTO
import com.tgyuu.network.source.chatting.ChattingDataSource
import javax.inject.Inject

class AiChattingRepositoryImpl @Inject constructor(
    private val chattingDataSource: ChattingDataSource,
    private val aiChattingDao: AiChattingDao,
) : AiChattingRepository {
    override suspend fun postAiMessage(chatLog: List<AiMessage>): Result<AiMessages> = runCatching {
        val aiChatResponse = chattingDataSource.postAiMessage(
            AiChatRequest(
                messageDTO = chatLog.map {
                    MessageDTO(
                        content = it.content,
                        role = it.role.name.lowercase(),
                    )
                },
            ),
        )

        val aiMessages = mutableListOf<AiMessage>()
        aiChatResponse.map { chatResponse ->
            chatResponse.choices.map {
                aiMessages.add(
                    AiMessage(
                        content = it.messageDTO.content,
                        role = when (it.messageDTO.role) {
                            "user" -> ChattingRole.USER
                            "system" -> ChattingRole.SYSTEM
                            "assistant" -> ChattingRole.ASSISTANT
                            else -> ChattingRole.FUNCTION
                        },
                    ),
                )
            }
        }

        AiMessages(aiMessages = aiMessages)
    }

    override suspend fun insertMessage(
        id: String,
        chattingRoomId: String,
        messageFrom: String,
        messageTo: String,
        content: String,
        createdAt: String,
    ) = runCatching {
        aiChattingDao.insertMessage(
            AiMessageEntity(
                id,
                chattingRoomId,
                messageFrom,
                messageTo,
                content,
                createdAt,
            ),
        )
    }

    override suspend fun insertChattingRoom(id: String, lastChatting: String, updatedAt: String) =
        runCatching {
            aiChattingDao.insertChattingRoom(
                ChattingRoomEntity(
                    id,
                    lastChatting,
                    updatedAt,
                ),
            )
        }

    override suspend fun deleteChattingRoom(id: String) = runCatching {
        aiChattingDao.deleteChattingRoom(roomId = id)
        aiChattingDao.deleteAllChattingRoomMessages(roomId = id)
    }

    override suspend fun getChattingRoom(roomId: String): Result<ChattingRoom> = runCatching {
        val chattingRoomEntity = aiChattingDao.getChattingRoom(roomId)
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

    override suspend fun getAllChattingRoomMessages(roomId: String): Result<List<Message>> =
        runCatching {
            aiChattingDao.getAllChattingRoomMessages(roomId).map {
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

    override suspend fun getLocalAllChattingRoom(): Result<List<ChattingRoom>> = runCatching {
        aiChattingDao.getAllChattingRoom().map {
            ChattingRoom(
                id = it.id,
                lastMessage = it.lastMessage,
                lastUpdated = it.lastUpdated,
            )
        }
    }
}
