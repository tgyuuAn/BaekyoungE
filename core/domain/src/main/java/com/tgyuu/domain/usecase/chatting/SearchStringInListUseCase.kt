package com.tgyuu.domain.usecase.chatting

import com.tgyuu.model.chatting.AiMessage
import com.tgyuu.model.chatting.ChattingRole.FUNCTION
import com.tgyuu.model.chatting.ChattingRole.SYSTEM
import com.tgyuu.model.chatting.MessageContentProvider
import javax.inject.Inject

data class SearchResult(
    val initialMatch: Pair<Int, List<IntRange>>? = null,
    val previousMatchIndex: Int? = null,
    val nextMatchIndex: Int? = null,
)

class SearchStringInListUseCase @Inject constructor() {
    operator fun invoke(
        nowIndex: Int?,
        messages: List<MessageContentProvider>,
        text: String
    ): SearchResult {
        val searchIndex = nowIndex ?: findFirstSearch(messages, text)

        if (searchIndex == null) {
            return SearchResult(null, null, null)
        }

        val searchPositions = findTextPositions(messages[searchIndex].content, text)

        // 위로 탐색
        val upperIndex = findDirectionalSearch(messages, searchIndex, text, true)

        // 아래로 탐색
        val underIndex = findDirectionalSearch(messages, searchIndex, text, false)

        return SearchResult(Pair(searchIndex, searchPositions), upperIndex, underIndex)
    }

    private fun findTextPositions(textContent: String, searchText: String): List<IntRange> {
        val matches = Regex(searchText).findAll(textContent)
        return matches.map { it.range }.toList()
    }

    private fun findDirectionalSearch(
        messages: List<MessageContentProvider>,
        startIndex: Int,
        searchText: String,
        isUp: Boolean
    ): Int? {
        if (isUp) {
            for (index in (startIndex - 1) downTo 0) {
                if (messages[index] is AiMessage && shouldSkipMessage(messages[index] as AiMessage)) continue

                if (Regex(searchText).containsMatchIn(messages[index].content)) {
                    return index
                }
            }
        } else {
            for (index in (startIndex + 1) until messages.size) {
                if (messages[index] is AiMessage && shouldSkipMessage(messages[index] as AiMessage)) continue

                if (Regex(searchText).containsMatchIn(messages[index].content)) {
                    return index
                }
            }
        }
        return null
    }

    private fun findFirstSearch(messages: List<MessageContentProvider>, searchText: String): Int? {
        for (index in messages.indices.reversed()) {
            if (messages[index] is AiMessage && shouldSkipMessage(messages[index] as AiMessage)) continue

            if (Regex(searchText).containsMatchIn(messages[index].content)) {
                return index
            }
        }
        return null
    }

    private fun shouldSkipMessage(message: AiMessage): Boolean {
        return message.role == SYSTEM || message.role == FUNCTION
    }
}
