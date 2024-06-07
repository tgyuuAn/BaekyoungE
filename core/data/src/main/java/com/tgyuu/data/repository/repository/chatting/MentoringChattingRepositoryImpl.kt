package com.tgyuu.data.repository.repository.chatting

import com.tgyuu.domain.repository.chatting.MentoringChattingRepository
import com.tgyuu.model.chatting.JoinChat
import com.tgyuu.model.chatting.MentoringMessage
import com.tgyuu.network.model.chatting.mentoring.JoinChatRequest
import com.tgyuu.network.model.chatting.mentoring.MentoringChatRequest
import com.tgyuu.network.source.auth.AuthDataSource
import com.tgyuu.network.source.chatting.ChattingDataSource
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MentoringChattingRepositoryImpl @Inject constructor(
    private val chattingDataSource: ChattingDataSource,
    private val authDataSource: AuthDataSource,
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
            lastChatting = content,
            lastSentTime = createdAt,
        ),
    )

    override suspend fun subscribeMessages(roomId: String): Flow<MentoringMessage> =
        chattingDataSource.subscribeMessages(roomId).map {
            MentoringMessage(
                roomId = it.roomId,
                userId = it.userId,
                content = it.content,
                createdAt = it.createdAt,
                isChecked = it.isChecked,
            )
        }

    override suspend fun getPreviousMessages(
        roomId: String,
        lastTime: String,
    ): Result<List<MentoringMessage>> =
        chattingDataSource.getPreviousMessages(roomId, lastTime).mapCatching {
            it.map {
                MentoringMessage(
                    roomId = it.roomId,
                    userId = it.userId,
                    content = it.content,
                    createdAt = it.createdAt,
                    isChecked = it.isChecked,
                )
            }
        }

    override suspend fun getMentorChattingRoom(userId: String): Result<List<JoinChat>> =
        coroutineScope {
            chattingDataSource.getMentorChattingRoom(userId).mapCatching { chatRooms ->
                chatRooms.map {
                    async {
                        val mentorInfoDeferred =
                            async { authDataSource.getUserInformation(it.mentorId) }
                        val menteeInfoDeferred =
                            async { authDataSource.getUserInformation(it.menteeId) }

                        JoinChat(
                            roomId = it.roomId,
                            mentorId = it.roomId.split("-")[0],
                            mentorNickName = mentorInfoDeferred.await().getOrThrow().nickName,
                            menteeId = it.roomId.split("-")[1],
                            menteeNickName = menteeInfoDeferred.await().getOrThrow().nickName,
                            lastChatting = it.lastChatting,
                            lastSentTime = it.lastSentTime,
                        )
                    }
                }.awaitAll()
            }
        }

    override suspend fun getMenteeChattingRoom(userId: String): Result<List<JoinChat>> =
        coroutineScope {
            chattingDataSource.getMenteeChattingRoom(userId).mapCatching { chatRooms ->
                chatRooms.map {
                    async {
                        val mentorInfoDeferred =
                            async { authDataSource.getUserInformation(it.mentorId) }
                        val menteeInfoDeferred =
                            async { authDataSource.getUserInformation(it.menteeId) }

                        JoinChat(
                            roomId = it.roomId,
                            mentorId = it.roomId.split("-")[0],
                            mentorNickName = mentorInfoDeferred.await().getOrThrow().nickName,
                            menteeId = it.roomId.split("-")[1],
                            menteeNickName = menteeInfoDeferred.await().getOrThrow().nickName,
                            lastChatting = it.lastChatting,
                            lastSentTime = it.lastSentTime,
                        )
                    }
                }.awaitAll()
            }
        }
}
