package com.pknu.network.di

import com.pknu.network.source.consulting.ConsultingDataSource
import com.pknu.network.source.consulting.ConsultingDataSourceImpl
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
    abstract fun bindsConsultingDataSource(
        consultingSourceImpl: ConsultingDataSourceImpl,
    ): ConsultingDataSource
}