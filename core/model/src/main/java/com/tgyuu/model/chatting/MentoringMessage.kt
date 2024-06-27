package com.tgyuu.model.chatting

interface MessageContentProvider {
    val content: String
}

data class MentoringMessage(
    val roomId: String,
    val fromUserId: String,
    val toUserId: String,
    override val content: String,
    val createdAt: String,
    val isChecked: Boolean
) : MessageContentProvider
