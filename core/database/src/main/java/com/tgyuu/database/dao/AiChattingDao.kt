package com.tgyuu.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tgyuu.database.model.ChattingRoomEntity
import com.tgyuu.database.model.AiMessageEntity

@Dao
interface AiChattingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: AiMessageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChattingRoom(chattingRoom: ChattingRoomEntity)

    @Query("DELETE FROM chatting_room WHERE id = :roomId")
    suspend fun deleteChattingRoom(roomId: String)

    @Query("DELETE FROM ai_message WHERE chatting_room_id = :roomId")
    suspend fun deleteAllChattingRoomMessages(roomId: String)

    @Query("SELECT * FROM ai_message WHERE chatting_room_id = :roomId ORDER BY created_at")
    suspend fun getAllChattingRoomMessages(roomId: String): List<AiMessageEntity>

    @Query("SELECT * FROM chatting_room ORDER BY last_updated DESC")
    suspend fun getAllChattingRoom(): List<ChattingRoomEntity>

    @Query("SELECT * FROM chatting_room WHERE id = :roomId")
    suspend fun getChattingRoom(roomId: String): ChattingRoomEntity?
}
