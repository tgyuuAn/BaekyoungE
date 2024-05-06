package com.tgyuu.network.model.consulting

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AiChatRequest(
    val model: String = "ft:gpt-3.5-turbo-0125:personal::9EYeVbEX:ckpt-step-1008",
    @SerialName("messages") val messageDTO: List<MessageDTO>,
)
