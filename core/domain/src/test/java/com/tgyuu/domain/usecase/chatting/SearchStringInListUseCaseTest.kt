package com.tgyuu.domain.usecase.chatting

import com.tgyuu.model.chatting.AiMessage
import com.tgyuu.model.chatting.ChattingRole
import org.junit.Assert.assertEquals
import org.junit.Test

class SearchStringInListUseCaseTest {
    private val searchStringInListUseCase = SearchStringInListUseCase()

    @Test
    fun `일치하는 텍스트가 없을 경우 인덱스를 반환하지 않는다`() {
        // given
        val chatList = listOf(
            AiMessage("", ChattingRole.USER),
            AiMessage("", ChattingRole.USER),
            AiMessage("", ChattingRole.USER)
        )
        val text = "가나"

        // when
        val actual = searchStringInListUseCase(null, chatList, text)

        // then
        val expected = SearchResult(null, null, null)
        assertEquals(expected, actual)
    }

    @Test
    fun `위쪽 메시지에서만 일치하는 텍스트가 있을 경우 위로가는 인덱스만 반환한다`() {
        // given
        val chatList = listOf(
            AiMessage("안녕하세요", ChattingRole.USER),
            AiMessage("안녕", ChattingRole.USER),
            AiMessage("", ChattingRole.USER)
        )
        val text = "안녕"

        // when
        val actual = searchStringInListUseCase(null, chatList, text)

        // then
        val expected = SearchResult(Pair(1, listOf(0..1)), 0, null)
        assertEquals(expected, actual)
    }

    @Test
    fun `아래쪽 메시지에서만 일치하는 텍스트가 있을 경우 아래로가는 인덱스만 반환한다`() {
        // given
        val nowIndex = 1
        val chatList = listOf(
            AiMessage("", ChattingRole.USER),
            AiMessage("가나", ChattingRole.USER),
            AiMessage("가나", ChattingRole.USER)
        )
        val text = "가나"

        // when
        val actual = searchStringInListUseCase(nowIndex, chatList, text)

        // then
        val expected = SearchResult(Pair(1, listOf(0..1)), null, 2)
        assertEquals(expected, actual)
    }

    @Test
    fun `일치하는 텍스트가 둘 다 있을 경우 위 아래로가는 인덱스 모두 반환한다`() {
        // given
        val nowIndex = 1
        val chatList = listOf(
            AiMessage("가나", ChattingRole.USER),
            AiMessage("가나", ChattingRole.USER),
            AiMessage("가나", ChattingRole.USER)
        )
        val text = "가나"

        // when
        val actual = searchStringInListUseCase(nowIndex, chatList, text)

        // then
        val expected = SearchResult(Pair(1, listOf(0..1)), 0, 2)
        assertEquals(expected, actual)
    }

    @Test
    fun `더 이상 위로 갈 수 없는 경우, 아래로가는 인덱스만 반환한다`() {
        // given
        val nowIndex = 0
        val chatList = listOf(
            AiMessage("가나", ChattingRole.USER),
            AiMessage("", ChattingRole.USER),
            AiMessage("가나", ChattingRole.USER)
        )
        val text = "가나"

        // when
        val actual = searchStringInListUseCase(nowIndex, chatList, text)

        // then
        val expected = SearchResult(Pair(0, listOf(0..1)), null, 2)
        assertEquals(expected, actual)
    }
}
