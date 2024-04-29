package com.tgyuu.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tgyuu.database.model.MessageEntity
import com.tgyuu.database.model.ChattingRoomEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChattingLogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: MessageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChattingRoom(chattingRoom: ChattingRoomEntity)

    @Query("SELECT * FROM message WHERE chatting_room_id MATCH :roomId ORDER BY created_at DESC")
    fun getAllChattingRoomMessages(roomId: String): Flow<List<MessageEntity>>

    @Query("SELECT * FROM chatting_room ORDER BY created_at DESC")
    fun getAllChattingRoom(): Flow<List<ChattingRoomEntity>>
}
