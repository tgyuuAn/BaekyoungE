package com.tgyuu.data.repository.repository.chatting

import com.tgyuu.domain.repository.chatting.MentoringChattingRepository
import com.tgyuu.model.chatting.JoinChat
import com.tgyuu.model.chatting.MentoringMessage
import com.tgyuu.network.model.chatting.mentoring.JoinChatRequest
import com.tgyuu.network.model.chatting.mentoring.MentoringChatRequest
import com.tgyuu.network.source.chatting.ChattingDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MentoringChattingRepositoryImpl @Inject constructor(
    private val chattingDataSource: ChattingDataSource,
) : MentoringChattingRepository {
    override suspend fun postMentoringChatMessage(
        roomId: String,
        userId: String,
        content: String,
        createdAt: String,
    ): Result<Unit> = chattingDataSource.postMentoringMessage(
        MentoringChatRequest(
            roomId = roomId,
            userId = userId,
            content = content,
            createdAt = createdAt,
            isChecked = false,
        ),
        JoinChatRequest(
            roomId = roomId,
            mentorId = roomId.split("-")[0],
            menteeId = roomId.split("-")[1],
        ),
    )

    override suspend fun getAllMessage(roomId: String): Flow<MentoringMessage> =
        chattingDataSource.getAllMessage(roomId).map {
            MentoringMessage(
                roomId = it.roomId,
                userId = it.userId,
                content = it.content,
                createdAt = it.createdAt,
                isChecked = it.isChecked,
            )
        }

    override suspend fun getMentorChattingRoom(userId: String): Result<List<JoinChat>> =
        chattingDataSource.getMentorChattingRoom(userId).mapCatching {
            it.map {
                JoinChat(
                    roomId = it.roomId,
                    mentorId = it.roomId.split("-")[0],
                    menteeId = it.roomId.split("-")[1],
                )
            }
        }

    override suspend fun getMenteeChattingRoom(userId: String): Result<List<JoinChat>> =
        chattingDataSource.getMenteeChattingRoom(userId).mapCatching {
            it.map {
                JoinChat(
                    roomId = it.roomId,
                    mentorId = it.roomId.split("-")[0],
                    menteeId = it.roomId.split("-")[1],
                )
            }
        }
}
