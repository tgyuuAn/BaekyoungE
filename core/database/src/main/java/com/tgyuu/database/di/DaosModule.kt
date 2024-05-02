package com.tgyuu.database.di

import com.tgyuu.database.BaekyoungDatabase
import com.tgyuu.database.dao.ChattingDao
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
    ): ChattingDao = database.ChattingDao()
}