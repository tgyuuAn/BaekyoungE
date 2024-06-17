package com.tgyuu.model.chatting

data class MentoringMessage(
    val roomId: String,
    val fromUserId: String,
    val toUserId: String,
    val content: String,
    val createdAt: String,
    val isChecked: Boolean,
)
