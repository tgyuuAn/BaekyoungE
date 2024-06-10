package com.tgyuu.baekyounge.main.di

import android.content.Context
import com.tgyuu.baekyounge.main.NetworkObserver
import com.tgyuu.baekyounge.main.NotificationHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNetworkObserver(
        @ApplicationContext context: Context,
    ): NetworkObserver = NetworkObserver(context)

    @Provides
    @Singleton
    fun provideNotificationHandler(
        @ApplicationContext context: Context,
    ): NotificationHandler = NotificationHandler(context)
}
