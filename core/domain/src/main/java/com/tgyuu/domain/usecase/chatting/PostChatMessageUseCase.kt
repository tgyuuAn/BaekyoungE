package com.tgyuu.domain.usecase.chatting

import android.util.Log
import com.tgyuu.data.repository.repository.chatting.ChattingRepository
import com.tgyuu.data.repository.repository.consulting.ConsultingRepository
import com.tgyuu.model.consulting.ChatLog
import com.tgyuu.model.consulting.ChattingRole
import com.tgyuu.model.consulting.Message
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class PostChatMessageUseCase @Inject constructor(
    private val consultingRepository: ConsultingRepository,
    private val chattingRepository: ChattingRepository,
) {
    suspend operator fun invoke(chatLog: List<Message>, roomId: String): Result<ChatLog> {
        chattingRepository.insertMessage(
            id = generateNowDateTime().toISOLocalDateTimeString(),
            chattingRoomId = roomId,
            messageFrom = ChattingRole.USER.name,
            messageTo = ChattingRole.ASSISTANT.name,
            content = chatLog.get(chatLog.size - 1).content,
            createdAt = generateNowDateTime().toISOLocalDateTimeString(),
        )

        val chattingRoom = chattingRepository.getChattingRoom(roomId).getOrThrow()

        Log.d("test", chattingRoom.toString())

        chattingRepository.insertChattingRoom(
            id = chattingRoom.id,
            lastChatting = chatLog.get(chatLog.size - 1).content,
            updatedAt = generateNowDateTime().toISOLocalDateTimeString(),
        )

        return consultingRepository.postChatMessage(chatLog).let {
            it.onSuccess {
                chattingRepository.insertMessage(
                    id = generateNowDateTime().toISOLocalDateTimeString(),
                    chattingRoomId = roomId,
                    messageFrom = ChattingRole.ASSISTANT.name,
                    messageTo =ChattingRole.USER.name,
                    content = it.messages.get(it.messages.size-1).content,
                    createdAt = generateNowDateTime().toISOLocalDateTimeString(),
                )

                chattingRepository.insertChattingRoom(
                    id = chattingRoom.id,
                    lastChatting = it.messages.get(it.messages.size-1).content,
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

