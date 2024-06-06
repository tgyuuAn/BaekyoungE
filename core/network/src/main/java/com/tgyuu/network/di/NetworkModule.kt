package com.tgyuu.network.di

import com.tgyuu.network.source.auth.AuthDataSource
import com.tgyuu.network.source.auth.AuthDataSourceImpl
import com.tgyuu.network.source.chatting.ChattingDataSource
import com.tgyuu.network.source.chatting.ChattingDataSourceImpl
import com.tgyuu.network.source.mentoring.MentoringDataSource
import com.tgyuu.network.source.mentoring.MentoringDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    @Singleton
    abstract fun bindsChattingDataSource(
        chattingDataSourceImpl: ChattingDataSourceImpl,
    ): ChattingDataSource

    @Binds
    @Singleton
    abstract fun bindsAuthDataSource(
        authDataSourceImpl: AuthDataSourceImpl,
    ): AuthDataSource

    @Binds
    @Singleton
    abstract fun bindsMentoringDataSource(
        mentoringDataSourceImpl: MentoringDataSourceImpl,
    ): MentoringDataSource
}
