package com.tgyuu.network.model.chatting.mentoring

data class JoinChatResponse(
    val mentorId: String = "",
    val menteeId: String = "",
    val roomId: String = "",
    val lastChatting: String = "",
    val lastSentTime: String = "",
)
