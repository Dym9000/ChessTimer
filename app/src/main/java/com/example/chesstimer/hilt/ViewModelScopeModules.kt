package com.example.chesstimer.hilt

import com.example.chesstimer.timeModesDatabase.TimeMode
import com.example.chesstimer.utils.TimeConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object ViewModelScopeModules {

    @Provides
    fun provideTimeConverter():TimeConverter{
        return TimeConverter()
    }

    @Provides
    fun provideTimeMode(): TimeMode{
        return TimeMode()
    }

//    @Provides
//    fun providePlayer(): Player{
//        return Player()
//    }

}