package com.tgyuu.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tgyuu.database.dao.ChattingDao
import com.tgyuu.database.model.ChattingRoomEntity
import com.tgyuu.database.model.MessageEntity

@Database(
    entities = [ChattingRoomEntity::class, MessageEntity::class],
    version = 1,
)
abstract class BaekyoungDatabase : RoomDatabase() {
    abstract fun ChattingDao(): ChattingDao
}
