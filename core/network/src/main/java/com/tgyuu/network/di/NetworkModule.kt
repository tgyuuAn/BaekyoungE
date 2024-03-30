package com.tgyuu.network.di

import com.tgyuu.network.source.auth.AuthDataSource
import com.tgyuu.network.source.auth.AuthDataSourceImpl
import com.tgyuu.network.source.consulting.ConsultingDataSource
import com.tgyuu.network.source.consulting.ConsultingDataSourceImpl
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
        consultingDataSourceImpl: ConsultingDataSourceImpl,
    ): ConsultingDataSource

    @Binds
    @Singleton
    abstract fun bindsAuthDataSource(
        authDataSourceImpl: AuthDataSourceImpl,
    ): AuthDataSource
}
