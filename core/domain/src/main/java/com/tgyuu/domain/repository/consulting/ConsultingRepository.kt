package com.tgyuu.domain.repository.consulting

import com.tgyuu.model.consulting.ChatLog
import com.tgyuu.model.consulting.Message

interface ConsultingRepository {
    suspend fun postChatMessage(chatLog: List<Message>): Result<ChatLog>
}
