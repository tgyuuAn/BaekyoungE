package com.tgyuu.database.di

import com.tgyuu.database.BaekyoungDatabase
import com.tgyuu.database.dao.AiChattingDao
import com.tgyuu.database.dao.MentorDao
import com.tgyuu.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {
    @Provides
    fun providesChattingDao(
        database: BaekyoungDatabase,
    ): AiChattingDao = database.AiChattingDao()

    @Provides
    fun providesUserDao(
        database: BaekyoungDatabase,
    ): UserDao = database.UserDao()

    @Provides
    fun providesMentorDao(
        database: BaekyoungDatabase,
    ): MentorDao = database.MentorDao()
}
