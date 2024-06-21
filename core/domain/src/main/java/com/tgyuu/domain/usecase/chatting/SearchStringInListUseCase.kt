package com.tgyuu.domain.usecase.chatting

import android.util.Log
import com.tgyuu.model.chatting.AiMessage
import javax.inject.Inject

class SearchStringInListUseCase @Inject constructor() {
    operator fun invoke(
        nowIndex: Int?,
        chatList: List<AiMessage>,
        text: String,
    ): Triple<Int?, Int?, Int?> {
        val searchIndex = nowIndex ?: findFirstSearch(nowIndex, chatList, text)

        // 위로 탐색
        var upperIndex: Int? = null
        if ((searchIndex - 1) >= 0) {
            for (idx in ((searchIndex - 1) downTo 0)) {
                if (chatList.get(idx).content.contains(text)) {
                    upperIndex = idx
                    break
                }
            }
        }

        // 아래로 탐색
        var underIndex: Int? = null
        if ((searchIndex + 1) < chatList.size) {
            for (idx in ((searchIndex + 1) until chatList.size)) {
                if (chatList.get(idx).content.contains(text)) {
                    underIndex = idx
                    break
                }
            }
        }

        Log.d("test", "검색 결과 : ${Triple(searchIndex, upperIndex, underIndex)}}")
        return Triple(searchIndex, upperIndex, underIndex)
    }

    private fun findFirstSearch(
        nowIndex: Int?,
        chatList: List<AiMessage>,
        text: String,
    ): Int {
        val searchIndex = nowIndex ?: (chatList.size)
        var upperIndex: Int? = null
        if ((searchIndex - 1) >= 0) {
            for (idx in ((searchIndex - 1) downTo 0)) {
                if (chatList.get(idx).content.contains(text)) {
                    upperIndex = idx
                    break
                }
            }
        }

        return upperIndex ?: chatList.size
    }
}
