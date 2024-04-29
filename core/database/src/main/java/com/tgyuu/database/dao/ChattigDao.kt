package com.tgyuu.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tgyuu.database.model.MessageEntity
import com.tgyuu.database.model.ChattingRoomEntity

@Dao
interface ChattingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: MessageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChattingRoom(chattingRoom: ChattingRoomEntity)

    @Query("SELECT * FROM message WHERE chatting_room_id MATCH :roomId ORDER BY created_at DESC")
    suspend fun getAllChattingRoomMessages(roomId: String): List<MessageEntity>

    @Query("SELECT * FROM chatting_room ORDER BY created_at DESC")
    suspend fun getAllChattingRoom(): List<ChattingRoomEntity>
}
