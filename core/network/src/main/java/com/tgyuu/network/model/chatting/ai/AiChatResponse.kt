package com.tgyuu.network.model.chatting.ai

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AiChatResponse(
    val id: String,
    @SerialName("object") val _object: String,
    val created: Long,
    val model: String,
    val system_fingerprint: String?,
    val choices: List<Choice>,
    val usage: Usage,
)

@Serializable
data class Choice(
    val index: Int,
    @SerialName("message") val messageDTO: MessageDTO,
    val logprobs: Int? = null,
    val finish_reason: String,
)

@Serializable
data class Usage(
    val prompt_tokens: Int,
    val completion_tokens: Int,
    val total_tokens: Int,
)
