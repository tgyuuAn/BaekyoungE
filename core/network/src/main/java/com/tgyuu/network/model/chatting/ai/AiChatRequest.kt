package com.tgyuu.network.model.chatting.ai

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AiChatRequest(
    val model: String = "ft:gpt-3.5-turbo-0125:personal::9VGdbeCe:ckpt-step-94",
    @SerialName("messages") val messageDTO: List<MessageDTO>,
)
