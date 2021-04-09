package com.example.chesstimer.hilt

import com.example.chesstimer.timeModes.TimeModesRepository
import com.example.chesstimer.timeModesDatabase.TimeModesDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TimeModesRepositoryModule{

    @Provides
    @Singleton
    fun provideTimeModesRepository(timeModesDAO: TimeModesDAO): TimeModesRepository {
        return TimeModesRepository(timeModesDAO)
    }
}