package com.tgyuu.domain.usecase.chatting

import com.tgyuu.domain.repository.chatting.AiChattingRepository
import com.tgyuu.model.chatting.AiMessage
import com.tgyuu.model.chatting.AiMessages
import com.tgyuu.model.chatting.ChattingRole
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class PostAiMessageUseCase @Inject constructor(
    private val aiChattingRepository: AiChattingRepository,
) {
    suspend operator fun invoke(chatLog: List<AiMessage>, roomId: String): Result<AiMessages> {
        aiChattingRepository.insertMessage(
            id = generateNowDateTime().toISOLocalDateTimeString(),
            chattingRoomId = roomId,
            messageFrom = ChattingRole.USER.name,
            messageTo = ChattingRole.ASSISTANT.name,
            content = chatLog.get(chatLog.size - 1).content,
            createdAt = generateNowDateTime().toISOLocalDateTimeString(),
        )

        aiChattingRepository.insertChattingRoom(
            id = roomId,
            lastChatting = chatLog.get(chatLog.size - 1).content,
            updatedAt = generateNowDateTime().toISOLocalDateTimeString(),
        )

        return aiChattingRepository.postAiMessage(chatLog).let {
            it.onSuccess {
                aiChattingRepository.insertMessage(
                    id = generateNowDateTime().toISOLocalDateTimeString(),
                    chattingRoomId = roomId,
                    messageFrom = ChattingRole.ASSISTANT.name,
                    messageTo = ChattingRole.USER.name,
                    content = it.aiMessages.get(it.aiMessages.size - 1).content,
                    createdAt = generateNowDateTime().plusSeconds(1).toISOLocalDateTimeString(),
                )

                aiChattingRepository.insertChattingRoom(
                    id = roomId,
                    lastChatting = it.aiMessages.get(it.aiMessages.size - 1).content,
                    updatedAt = generateNowDateTime().toISOLocalDateTimeString(),
                )
            }
        }
    }
}

internal fun LocalDateTime.toISOLocalDateTimeString(): String =
    this.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)

internal fun generateNowDateTime(zoneId: ZoneId = ZoneId.of("Asia/Seoul")): LocalDateTime =
    LocalDateTime.now(zoneId)
