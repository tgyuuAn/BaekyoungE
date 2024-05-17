package com.tgyuu.data.repository.repository.chatting

import com.tgyuu.domain.repository.chatting.RemoteChattingRepository
import com.tgyuu.model.chatting.AiMessage
import com.tgyuu.model.chatting.AiMessages
import com.tgyuu.model.chatting.ChattingRole
import com.tgyuu.model.chatting.MentoringMessage
import com.tgyuu.network.model.chatting.ai.AiChatRequest
import com.tgyuu.network.model.chatting.ai.MessageDTO
import com.tgyuu.network.model.chatting.mentoring.MentoringChatRequest
import com.tgyuu.network.source.chatting.ChattingDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteChattingRepositoryImpl @Inject constructor(
    private val chattingDataSource: ChattingDataSource,
) : RemoteChattingRepository {
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

    override suspend fun postMentoringChatMessage(
        roomId: String,
        userId: String,
        content: String,
        createdAt: String,
    ): Result<Unit> = chattingDataSource.postMentoringMessage(
        MentoringChatRequest(
            roomId = roomId,
            userId = userId,
            content = content,
            createdAt = createdAt,
            isChecked = false,
        ),
    )

    override suspend fun getAllMessage(roomId: String): Flow<MentoringMessage> =
        chattingDataSource.getAllMessage(roomId).map {
            MentoringMessage(
                roomId = it.roomId,
                userId = it.userId,
                content = it.content,
                createdAt = it.createdAt,
                isChecked = it.isChecked,
            )
        }
}
