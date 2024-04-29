package com.tgyuu.database.di

import android.content.Context
import androidx.room.Room
import com.tgyuu.database.BaekyoungDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesBaekyoungDatabase(
        @ApplicationContext context: Context,
    ): BaekyoungDatabase = Room.databaseBuilder(
        context,
        BaekyoungDatabase::class.java,
        "baekyoung-database",
    ).build()
}
