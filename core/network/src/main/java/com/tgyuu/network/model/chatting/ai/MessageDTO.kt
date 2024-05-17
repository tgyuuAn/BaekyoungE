package com.tgyuu.network.model.chatting.ai

import kotlinx.serialization.Serializable

@Serializable
data class MessageDTO(
    val role: String,
    val content: String,
)
