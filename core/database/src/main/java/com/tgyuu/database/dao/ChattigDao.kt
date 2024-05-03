package com.tgyuu.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tgyuu.database.model.ChattingRoomEntity
import com.tgyuu.database.model.MessageEntity

@Dao
interface ChattingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: MessageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChattingRoom(chattingRoom: ChattingRoomEntity)

    @Query("DELETE FROM chatting_room WHERE id = :roomId")
    suspend fun deleteChattingRoom(roomId: String)

    @Query("DELETE FROM message WHERE chatting_room_id = :roomId")
    suspend fun deleteAllChattingRoomMessages(roomId: String)

    @Query("SELECT * FROM message WHERE chatting_room_id = :roomId ORDER BY created_at DESC")
    suspend fun getAllChattingRoomMessages(roomId: String): List<MessageEntity>

    @Query("SELECT * FROM chatting_room ORDER BY last_updated DESC")
    suspend fun getAllChattingRoom(): List<ChattingRoomEntity>

    @Query("SELECT * FROM chatting_room WHERE id = :roomId")
    suspend fun getChattingRoom(roomId: String): ChattingRoomEntity?
}
