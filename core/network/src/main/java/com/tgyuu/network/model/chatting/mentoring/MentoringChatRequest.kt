package com.tgyuu.network.model.chatting.mentoring

data class MentoringChatRequest(
    val roomId: String,
    val fromUserId: String,
    val toUserId: String,
    val content: String,
    val createdAt: String,
    val isChecked: Boolean,
)
