package com.tgyuu.data.repository.repository.consulting

import com.tgyuu.domain.repository.consulting.ConsultingRepository
import com.tgyuu.model.consulting.ChatLog
import com.tgyuu.model.consulting.ChattingRole
import com.tgyuu.model.consulting.Message
import com.tgyuu.network.model.consulting.AiChatRequest
import com.tgyuu.network.model.consulting.MessageDTO
import com.tgyuu.network.source.consulting.AiConsultingDataSource
import javax.inject.Inject

class ConsultingRepositoryImpl @Inject constructor(
    private val aiConsultingDataSource: AiConsultingDataSource,
) : ConsultingRepository {
    override suspend fun postChatMessage(chatLog: List<Message>): Result<ChatLog> = runCatching {
        val aiChatResponse = aiConsultingDataSource.postChatMessage(
            AiChatRequest(
                messageDTO = chatLog.map {
                    MessageDTO(
                        content = it.content,
                        role = it.role.name.lowercase(),
                    )
                },
            ),
        )

        val messages = mutableListOf<Message>()
        aiChatResponse.map { chatResponse ->
            chatResponse.choices.map {
                messages.add(
                    Message(
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

        ChatLog(messages = messages)
    }
}
