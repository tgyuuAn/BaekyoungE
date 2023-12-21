package com.pknu.network.model.consulting

import com.pknu.model.consulting.ConsultingChatting

data class ConsultingResponse(
    val chat_assistant: String? = null,
    val chat_user: String? = null,
) {
    fun toConsultingChatting(): ConsultingChatting =
        ConsultingChatting(
            chatAssistant = this.chat_assistant ?: "",
            chatUser = this.chat_user ?: "",
        )
}