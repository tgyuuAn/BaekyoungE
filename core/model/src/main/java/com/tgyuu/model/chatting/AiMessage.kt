package com.tgyuu.model.chatting

data class AiMessages(
    val aiMessages: MutableList<AiMessage>,
)

data class AiMessage(
    override val content: String,
    val role: ChattingRole
) : MessageContentProvider

enum class ChattingRole {
    USER, ASSISTANT, SYSTEM, FUNCTION
}
