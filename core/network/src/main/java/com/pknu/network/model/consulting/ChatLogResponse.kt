package com.pknu.network.model.consulting

import com.pknu.model.consulting.ChattingRole
import com.pknu.model.consulting.ConsultingChatting

data class ConsultingResponse(
    val chat_log: List<ChatLogResponse> = listOf(),
)

data class ChatLogResponse(
    val content: String? = null,
    val role: String? = null,
) {
    fun toConsultingChatting(): ConsultingChatting =
        ConsultingChatting(
            content = this.content ?: "",
            role = when (this.role) {
                "user" -> ChattingRole.USER
                "system" -> ChattingRole.SYSTEM
                "assistant" -> ChattingRole.ASSISTANT
                else -> throw IllegalArgumentException("잘못된 역할 입니다.")
            }
        )
}