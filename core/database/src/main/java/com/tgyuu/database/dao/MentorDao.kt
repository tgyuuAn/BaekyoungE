package com.tgyuu.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tgyuu.database.model.MentorEntity

@Dao
interface MentorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMentorInformation(mentor: MentorEntity)

    @Query("DELETE FROM mentor WHERE user_id = :userId")
    suspend fun deleteMentorInformation(userId: String)

    @Query("SELECT * FROM mentor LIMIT 1")
    suspend fun getMentorInformation(): MentorEntity?
}
