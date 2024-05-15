package com.tgyuu.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tgyuu.database.model.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInformation(user: UserEntity)

    @Update
    suspend fun updateUserInformation(user: UserEntity)

    @Query("DELETE FROM user WHERE id = :userId")
    suspend fun deleteUserInformation(userId: String)

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun getUserInformation(): UserEntity?
}
