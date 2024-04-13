package com.tgyuu.network.model.consulting

import kotlinx.serialization.Serializable

@Serializable
data class MessageDTO(
    val role: String,
    val content: String,
)
