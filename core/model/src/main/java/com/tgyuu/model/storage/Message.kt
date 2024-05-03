package com.tgyuu.model.storage

data class Message(
    val id: String,
    val chattingRoomId: String,
    val messageFrom: String,
    val messageTo: String,
    val content: String,
    val createdAt: String,
)
