package com.tgyuu.network.model.chatting.mentoring

data class JoinChatRequest(
    val mentorId: String,
    val menteeId: String,
    val roomId: String,
)
