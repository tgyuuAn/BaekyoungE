package com.tgyuu.network.model.chatting.mentoring

data class MentoringChatRequest(
    val roomId: String,
    val userId: String,
    val content: String,
    val createdAt: String,
    val isChecked: Boolean,
)
