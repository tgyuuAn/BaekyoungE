package com.tgyuu.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "nick_name")
    val nickName: String,
    val gender: String,
    val major: String,
    val grade: Int,
    @ColumnInfo(name = "registration_date")
    val registrationDate: String,
)
