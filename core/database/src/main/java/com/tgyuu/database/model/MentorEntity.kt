package com.tgyuu.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mentor")
data class MentorEntity(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val userId: String,
    @ColumnInfo(name = "nick_name")
    val nickName: String,
    @ColumnInfo(name = "registration_date")
    val registrationDate: String,
)
