package com.tgyuu.model.chatting

data class AiMessages(
    val aiMessages: MutableList<AiMessage>,
)

data class AiMessage(
    val content: String,
    val role: ChattingRole,
)

enum class ChattingRole {
    USER, ASSISTANT, SYSTEM, FUNCTION
}
