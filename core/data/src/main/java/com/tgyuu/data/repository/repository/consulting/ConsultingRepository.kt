package com.tgyuu.data.repository.repository.consulting

import com.tgyuu.model.consulting.ChatLog

interface ConsultingRepository {
    suspend fun postChatMessage(chatLog: ChatLog): Result<ChatLog>
}
