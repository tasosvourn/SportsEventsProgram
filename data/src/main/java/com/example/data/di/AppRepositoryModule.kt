package com.example.data.di

import com.example.data.networking.RestApiService
import com.example.data.repository.UpcomingSportsEventsRepositoryImpl
import com.example.domain.repository.UpcomingSportsEventsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppRepositoryModule {

    @Provides
    fun provideUpcomingSportsEventsRepository(
        restApiService: RestApiService
    ) : UpcomingSportsEventsRepository = UpcomingSportsEventsRepositoryImpl(restApiService)
}