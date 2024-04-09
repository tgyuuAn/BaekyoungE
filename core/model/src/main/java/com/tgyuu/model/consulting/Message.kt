package com.tgyuu.model.consulting

data class Message(
    val content: String,
    val role: ChattingRole,
)

enum class ChattingRole {
    USER, ASSISTANT, SYSTEM
}
