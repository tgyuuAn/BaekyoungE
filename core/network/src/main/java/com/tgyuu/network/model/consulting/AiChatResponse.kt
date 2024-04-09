package com.tgyuu.network.model.consulting

data class AiChatResponse(
    val id: String,
    val _object: String,
    val created: Long,
    val model: String,
    val system_fingerprint: String,
    val choices: List<Choice>,
    val usage: Usage,
)

data class Choice(
    val index: Int,
    val message: Message,
    val logprobs: Any?, // null 가능성이 있어 Any?로 선언합니다. 필요에 따라 구체적인 타입으로 변경할 수 있습니다.
    val finish_reason: String,
)

data class Usage(
    val prompt_tokens: Int,
    val completion_tokens: Int,
    val total_tokens: Int,
)
