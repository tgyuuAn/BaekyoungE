package com.tgyuu.data.repository.di

import com.tgyuu.data.repository.repository.auth.AuthRepositoryImpl
import com.tgyuu.data.repository.repository.chatting.AiChattingRepositoryImpl
import com.tgyuu.data.repository.repository.chatting.MentoringChattingRepositoryImpl
import com.tgyuu.data.repository.repository.mentoring.MentoringRepositoryImpl
import com.tgyuu.domain.repository.auth.AuthRepository
import com.tgyuu.domain.repository.chatting.AiChattingRepository
import com.tgyuu.domain.repository.chatting.MentoringChattingRepository
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
    abstract fun bindsAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl,
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindsAiChattingRepository(
        aiChattingRepositoryImpl: AiChattingRepositoryImpl,
    ): AiChattingRepository

    @Binds
    @Singleton
    abstract fun bindsMentoringChattingRepository(
        mentoringChattingRepositoryImpl: MentoringChattingRepositoryImpl,
    ): MentoringChattingRepository

    @Binds
    @Singleton
    abstract fun bindsMentoringRepository(
        mentoringRepositoryImpl: MentoringRepositoryImpl,
    ): RemoteMentoringRepository
}
