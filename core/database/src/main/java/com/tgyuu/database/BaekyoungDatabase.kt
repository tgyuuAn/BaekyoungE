package com.tgyuu.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tgyuu.database.dao.AiChattingDao
import com.tgyuu.database.dao.MentorDao
import com.tgyuu.database.dao.UserDao
import com.tgyuu.database.model.ChattingRoomEntity
import com.tgyuu.database.model.MentorEntity
import com.tgyuu.database.model.AiMessageEntity
import com.tgyuu.database.model.UserEntity

@Database(
    entities = [
        ChattingRoomEntity::class,
        AiMessageEntity::class,
        UserEntity::class,
        MentorEntity::class,
    ],
    version = 1,
)
abstract class BaekyoungDatabase : RoomDatabase() {
    abstract fun AiChattingDao(): AiChattingDao
    abstract fun UserDao(): UserDao
    abstract fun MentorDao(): MentorDao
}
