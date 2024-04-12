package com.tgyuu.network.model.consulting

import kotlinx.serialization.Serializable

@Serializable
data class AiChatRequest(
    val model: String = "gpt-3.5-turbo",
    val messages: List<Message>,
)
