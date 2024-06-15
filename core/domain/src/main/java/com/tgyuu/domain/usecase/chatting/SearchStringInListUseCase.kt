package com.tgyuu.domain.usecase.chatting

import com.tgyuu.model.storage.Message
import javax.inject.Inject

class SearchStringInListUseCase @Inject constructor() {
    operator fun invoke(chatList: List<Message>, text: String): List<Message> =
        chatList.filter { chat ->
            chat.content.contains(text)
        }
}
