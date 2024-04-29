package com.tgyuu.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tgyuu.database.dao.ChattingLogDao
import com.tgyuu.database.model.MessageEntity
import com.tgyuu.database.model.ChattingRoomEntity

@Database(
    entities = [
        ChattingRoomEntity::class,
        MessageEntity::class,
    ],
    version = 1,
)
abstract class BaekyoungDatabase : RoomDatabase() {
    abstract fun ChattingLogDao(): ChattingLogDao
}
