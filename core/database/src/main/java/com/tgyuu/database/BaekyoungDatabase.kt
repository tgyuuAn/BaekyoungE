package com.tgyuu.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tgyuu.database.dao.ChattingDao
import com.tgyuu.database.dao.UserDao
import com.tgyuu.database.model.ChattingRoomEntity
import com.tgyuu.database.model.MessageEntity
import com.tgyuu.database.model.UserEntity

@Database(
    entities = [ChattingRoomEntity::class, MessageEntity::class, UserEntity::class],
    version = 1,
)
abstract class BaekyoungDatabase : RoomDatabase() {
    abstract fun ChattingDao(): ChattingDao
    abstract fun UserDao(): UserDao
}
