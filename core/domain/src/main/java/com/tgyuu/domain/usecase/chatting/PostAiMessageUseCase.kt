package com.tgyuu.domain.usecase.chatting

import com.tgyuu.domain.repository.chatting.LocalChattingRepository
import com.tgyuu.domain.repository.chatting.RemoteChattingRepository
import com.tgyuu.model.chatting.AiMessages
import com.tgyuu.model.chatting.ChattingRole
import com.tgyuu.model.chatting.AiMessage
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class PostAiMessageUseCase @Inject constructor(
    private val remoteChattingRepository: RemoteChattingRepository,
    private val localChattingRepository: LocalChattingRepository,
) {
    suspend operator fun invoke(chatLog: List<AiMessage>, roomId: String): Result<AiMessages> {
        localChattingRepository.insertLocalMessage(
            id = generateNowDateTime().toISOLocalDateTimeString(),
            chattingRoomId = roomId,
            messageFrom = ChattingRole.USER.name,
            messageTo = ChattingRole.ASSISTANT.name,
            content = chatLog.get(chatLog.size - 1).content,
            createdAt = generateNowDateTime().toISOLocalDateTimeString(),
        )

        localChattingRepository.insertLocalChattingRoom(
            id = roomId,
            lastChatting = chatLog.get(chatLog.size - 1).content,
            updatedAt = generateNowDateTime().toISOLocalDateTimeString(),
        )

        return remoteChattingRepository.postAiMessage(chatLog).let {
            it.onSuccess {
                localChattingRepository.insertLocalMessage(
                    id = generateNowDateTime().toISOLocalDateTimeString(),
                    chattingRoomId = roomId,
                    messageFrom = ChattingRole.ASSISTANT.name,
                    messageTo = ChattingRole.USER.name,
                    content = it.aiMessages.get(it.aiMessages.size - 1).content,
                    createdAt = generateNowDateTime().plusSeconds(1).toISOLocalDateTimeString(),
                )

                localChattingRepository.insertLocalChattingRoom(
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

