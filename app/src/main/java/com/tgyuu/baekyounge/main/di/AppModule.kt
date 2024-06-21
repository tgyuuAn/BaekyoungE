package com.tgyuu.baekyounge.main.di

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.tgyuu.baekyounge.main.NetworkObserver
import com.tgyuu.baekyounge.main.notification.NotificationHandler
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

    @Provides
    @Singleton
    fun provideFirebaseAnalytics(): FirebaseAnalytics = Firebase.analytics
}
