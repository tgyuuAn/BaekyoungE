package com.tgyuu.network.model.consulting

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AiChatRequest(
    val model: String = "gpt-3.5-turbo",
    @SerialName("messages") val messageDTO: List<MessageDTO>,
)
