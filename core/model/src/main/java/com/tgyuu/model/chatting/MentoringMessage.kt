package com.tgyuu.model.chatting

data class MentoringMessage(
    val roomId: String,
    val messageId: String,
    val userId: String,
    val content: String,
    val createdAt: String,
)
