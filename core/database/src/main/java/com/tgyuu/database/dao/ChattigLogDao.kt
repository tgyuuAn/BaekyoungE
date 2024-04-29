package com.tgyuu.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tgyuu.database.model.ChattingLogDetailEntity
import com.tgyuu.database.model.ChattingLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChattigLogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChattingLog(chattingLog: ChattingLogDetailEntity)

    @Query("SELECT * FROM chatting_log_detail WHERE id MATCH :id ORDER BY publishDate DESC")
    fun getChattingLogDetail(id: String): Flow<List<ChattingLogDetailEntity>>

    @Query("SELECT * FROM chatting_log ORDER BY publishDate DESC")
    fun getAllChattingLogs(): Flow<List<ChattingLogEntity>>
}
