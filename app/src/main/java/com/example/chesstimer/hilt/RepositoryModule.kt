package com.example.chesstimer.hilt

import com.example.chesstimer.displayTimeModesFragment.TimeModesRepository
import com.example.chesstimer.timeModesDatabase.TimeModesDAO
import com.example.chesstimer.utils.CountdownChessTimer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTimeModesRepository(timeModesDAO: TimeModesDAO): TimeModesRepository {
        return TimeModesRepository(timeModesDAO)
    }

    @Provides
    fun provideCountdownTimerSetter(): CountdownChessTimer {
        return CountdownChessTimer()
    }
}