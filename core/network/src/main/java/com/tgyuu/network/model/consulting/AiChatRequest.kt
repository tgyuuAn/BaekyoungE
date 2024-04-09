package com.tgyuu.network.model.consulting

data class AiChatRequest(
    val model: String = "gpt-3.5-turbo",
    val messages: List<Message>,
)
