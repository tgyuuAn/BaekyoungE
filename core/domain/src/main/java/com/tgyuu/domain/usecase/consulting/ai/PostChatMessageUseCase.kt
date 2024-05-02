package com.tgyuu.domain.usecase.consulting.ai

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
        Log.d("test", chatLog.toString())

        chattingRepository.insertMessage(
            id = generateNowDateTime().toISOLocalDateTimeString(),
            chattingRoomId = roomId,
            messageFrom = ChattingRole.USER.name,
            messageTo = ChattingRole.ASSISTANT.name,
            content = chatLog.get(chatLog.size - 1).content,
            createdAt = generateNowDateTime().toISOLocalDateTimeString(),
        )

        return consultingRepository.postChatMessage(chatLog).let {
            it.onSuccess {
                Log.d("test", it.toString())

                chattingRepository.insertMessage(
                    id = generateNowDateTime().toISOLocalDateTimeString(),
                    chattingRoomId = roomId,
                    messageFrom = ChattingRole.ASSISTANT.name,
                    messageTo =ChattingRole.USER.name,
                    content = it.messages.get(it.messages.size-1).content,
                    createdAt = generateNowDateTime().toISOLocalDateTimeString(),
                )
            }
        }
    }
}

internal fun LocalDateTime.toISOLocalDateTimeString(): String =
    this.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)

internal fun generateNowDateTime(zoneId: ZoneId = ZoneId.of("Asia/Seoul")): LocalDateTime =
    LocalDateTime.now(zoneId)

