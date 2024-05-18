package com.tgyuu.domain.usecase.chatting

import com.tgyuu.domain.repository.chatting.RemoteChattingRepository
import com.tgyuu.model.chatting.JoinChat
import javax.inject.Inject

class GetRemoteAllChattingRoomUseCase @Inject constructor(
    private val remoteChattingRepository: RemoteChattingRepository,
) {
    suspend operator fun invoke(userId: String): Result<List<JoinChat>> =
        remoteChattingRepository.getRemoteAllChattingRoom(userId)
}
