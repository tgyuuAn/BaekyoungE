package com.tgyuu.data.repository.di

import com.tgyuu.data.repository.repository.auth.AuthRepositoryImpl
import com.tgyuu.data.repository.repository.chatting.ChattingRepositoryImpl
import com.tgyuu.data.repository.repository.consulting.ConsultingRepositoryImpl
import com.tgyuu.data.repository.repository.mentoring.MentoringRepositoryImpl
import com.tgyuu.domain.repository.auth.AuthRepository
import com.tgyuu.domain.repository.chatting.ChattingRepository
import com.tgyuu.domain.repository.consulting.ConsultingRepository
import com.tgyuu.domain.repository.mentoring.MentoringRepository
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
    abstract fun bindsConsultingRepository(
        consultingRepositoryImpl: ConsultingRepositoryImpl,
    ): ConsultingRepository

    @Binds
    @Singleton
    abstract fun bindsAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl,
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindsChattingRepository(
        chattingRepositoryImpl: ChattingRepositoryImpl,
    ): ChattingRepository

    @Binds
    @Singleton
    abstract fun bindsMentoringRepository(
        mentoringRepositoryImpl: MentoringRepositoryImpl,
    ): MentoringRepository
}
