package com.tgyuu.network.model.chatting.mentoring

data class JoinChatResponse(
    val roomId: String = "",
    val userId: List<String> = listOf(),
)
