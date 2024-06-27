package com.tgyuu.domain.usecase.chatting

import com.tgyuu.model.chatting.AiMessage
import com.tgyuu.model.chatting.ChattingRole.FUNCTION
import com.tgyuu.model.chatting.ChattingRole.SYSTEM
import javax.inject.Inject

data class SearchResult(
    val initialMatch: Pair<Int, List<IntRange>>? = null,
    val previousMatchIndex: Int? = null,
    val nextMatchIndex: Int? = null,
)

class SearchStringInListUseCase @Inject constructor() {
    operator fun invoke(
        nowIndex: Int?,
        chatList: List<AiMessage>,
        text: String,
    ): SearchResult {
        val searchIndex = nowIndex ?: findFirstSearch(chatList, text)

        if (searchIndex == null) {
            return SearchResult(null, null, null)
        }

        val searchPositions = findTextPositions(chatList[searchIndex].content, text)

        // 위로 탐색
        val upperIndex: Int? = findDirectionalSearch(chatList, searchIndex, text, true)

        // 아래로 탐색
        val underIndex: Int? = findDirectionalSearch(chatList, searchIndex, text, false)

        return SearchResult(Pair(searchIndex, searchPositions), upperIndex, underIndex)
    }

    private fun findTextPositions(textContent: String, searchText: String): List<IntRange> {
        val matches = Regex(searchText).findAll(textContent)
        return matches.map { it.range }.toList()
    }

    private fun findDirectionalSearch(
        chatList: List<AiMessage>,
        startIndex: Int,
        searchText: String,
        isUp: Boolean,
    ): Int? {
        if (isUp) {
            for (index in (startIndex - 1) downTo 0) {
                if (shouldSkipMessage(chatList[index])) continue

                if (Regex(searchText).containsMatchIn(chatList[index].content)) {
                    return index
                }
            }
        } else {
            for (index in (startIndex + 1) until chatList.size) {
                if (shouldSkipMessage(chatList[index])) continue

                if (Regex(searchText).containsMatchIn(chatList[index].content)) {
                    return index
                }
            }
        }
        return null
    }

    private fun findFirstSearch(chatList: List<AiMessage>, searchText: String): Int? {
        for (index in chatList.indices.reversed()) {
            if (shouldSkipMessage(chatList[index])) continue

            if (Regex(searchText).containsMatchIn(chatList[index].content)) {
                return index
            }
        }
        return null
    }

    private fun shouldSkipMessage(message: AiMessage): Boolean {
        return message.role == SYSTEM || message.role == FUNCTION
    }
}
