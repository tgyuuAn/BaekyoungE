package com.tgyuu.network.model.consulting

import com.tgyuu.model.consulting.ChattingRole
import com.tgyuu.model.consulting.Message

data class ConsultingResponse(
    val chat_log: List<ChatLogResponse> = listOf(),
)

data class ChatLogResponse(
    val content: String? = null,
    val role: String? = null,
) {
    fun toConsultingChatting(): Message =
        Message(
            content = content ?: "",
            role = when (role) {
                "user" -> ChattingRole.USER
                "system" -> ChattingRole.SYSTEM
                "assistant" -> ChattingRole.ASSISTANT
                else -> throw IllegalArgumentException("잘못된 역할 입니다.")
            },
        )
}
