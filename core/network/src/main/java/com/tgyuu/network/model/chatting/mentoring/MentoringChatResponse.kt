package com.tgyuu.network.model.chatting.mentoring

data class MentoringChatResponse(
    val roomId: String = "",
    val userId: String = "",
    val content: String = "",
    val createdAt: String = "",
    val isChecked: Boolean = false,
)
