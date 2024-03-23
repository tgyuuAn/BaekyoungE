package com.pknu.model.consulting

data class ConsultingChatting(
    val content: String,
    val role: ChattingRole,
)

enum class ChattingRole {
    USER, ASSISTANT, SYSTEM
}
