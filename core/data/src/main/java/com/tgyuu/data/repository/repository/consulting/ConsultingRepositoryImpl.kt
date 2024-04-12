package com.tgyuu.data.repository.repository.consulting

import android.util.Log
import com.tgyuu.model.consulting.ChatLog
import com.tgyuu.model.consulting.ChattingRole
import com.tgyuu.network.model.consulting.AiChatRequest
import com.tgyuu.network.model.consulting.Message
import com.tgyuu.network.source.consulting.AiConsultingDataSource
import javax.inject.Inject

class ConsultingRepositoryImpl @Inject constructor(
    private val aiConsultingDataSource: AiConsultingDataSource,
) : ConsultingRepository {
    override suspend fun postChatMessage(chatLog: ChatLog): Result<ChatLog> = runCatching {
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

        Log.d("test", "aiChatResponse : $aiChatResponse")

        val messages = mutableListOf<com.tgyuu.model.consulting.Message>()

        aiChatResponse.map { chatResponse ->
            chatResponse.choices.map {
                messages.add(
                    com.tgyuu.model.consulting.Message(
                        content = it.message.content,
                        role = when (it.message.role) {
                            "user" -> ChattingRole.USER
                            "system" -> ChattingRole.SYSTEM
                            "assistant" -> ChattingRole.ASSISTANT
                            else -> ChattingRole.ERROR
                        },
                    ),
                )
            }
        }

        ChatLog(messages = messages)
    }
}
