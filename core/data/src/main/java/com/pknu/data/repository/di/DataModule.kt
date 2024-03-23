package com.pknu.data.repository.di

import com.pknu.data.repository.repository.consulting.ConsultingRepository
import com.pknu.data.repository.repository.consulting.ConsultingRepositoryImpl
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
}
