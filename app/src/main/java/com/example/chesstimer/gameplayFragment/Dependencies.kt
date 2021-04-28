package com.example.chesstimer.gameplayFragment

import com.example.chesstimer.utils.CountdownChessTimer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object Dependencies {

    @Provides
    fun provideCountdownTimerSetter(): CountdownChessTimer {
        return CountdownChessTimer()
    }

}