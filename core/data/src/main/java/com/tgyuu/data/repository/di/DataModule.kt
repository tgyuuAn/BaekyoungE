package com.tgyuu.data.repository.di

import com.tgyuu.data.repository.repository.auth.AuthRepositoryImpl
import com.tgyuu.data.repository.repository.chatting.LocalChattingRepositoryImpl
import com.tgyuu.data.repository.repository.chatting.RemoteChattingRepositoryImpl
import com.tgyuu.data.repository.repository.mentoring.LocalMentoringRepositoryImpl
import com.tgyuu.data.repository.repository.mentoring.RemoteMentoringRepositoryImpl
import com.tgyuu.domain.repository.auth.AuthRepository
import com.tgyuu.domain.repository.chatting.LocalChattingRepository
import com.tgyuu.domain.repository.chatting.RemoteChattingRepository
import com.tgyuu.domain.repository.mentoring.LocalMentoringRepository
import com.tgyuu.domain.repository.mentoring.RemoteMentoringRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindsRemoteChattingRepository(
        remoteChattingRepositoryImpl: RemoteChattingRepositoryImpl,
    ): RemoteChattingRepository

    @Binds
    @Singleton
    abstract fun bindsAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl,
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindsLocalChattingRepository(
        localChattingRepositoryImpl: LocalChattingRepositoryImpl,
    ): LocalChattingRepository

    @Binds
    @Singleton
    abstract fun bindsRemoteMentoringRepository(
        remoteMentoringRepositoryImpl: RemoteMentoringRepositoryImpl,
    ): RemoteMentoringRepository

    @Binds
    @Singleton
    abstract fun bindsLocalMentoringRepository(
        localMentoringRepositoryImpl: LocalMentoringRepositoryImpl,
    ): LocalMentoringRepository
}
