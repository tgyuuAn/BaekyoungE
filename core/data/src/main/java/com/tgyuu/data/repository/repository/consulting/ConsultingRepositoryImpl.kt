package com.tgyuu.data.repository.repository.consulting

import com.tgyuu.model.consulting.ChatLog
import com.tgyuu.model.consulting.ChattingRole
import com.tgyuu.network.model.consulting.AiChatRequest
import com.tgyuu.network.model.consulting.Message
import com.tgyuu.network.source.consulting.AiConsultingDataSource
import javax.inject.Inject

class ConsultingRepositoryImpl @Inject constructor(
    private val aiConsultingDataSource: AiConsultingDataSource,
) : ConsultingRepository {
    override suspend fun postChatMessage(chatLog: ChatLog): Result<ChatLog> {
        val aiChatResponse = aiConsultingDataSource.postChatMessage(
            AiChatRequest(
                messages = chatLog.messages.map {
                    Message(
                        content = it.content,
                        role = it.role.name,
                    )
                },
            ),
        )

        return aiChatResponse.map { chatResponse ->
            ChatLog(
                messages = chatResponse.choices
                    .map {
                        com.tgyuu.model.consulting.Message(
                            content = it.message.content,
                            role = when (it.message.role) {
                                "user" -> ChattingRole.USER
                                "system" -> ChattingRole.SYSTEM
                                "assistant" -> ChattingRole.ASSISTANT
                                else -> ChattingRole.ERROR
                            },
                        )
                    },
            )
        }
    }
}
