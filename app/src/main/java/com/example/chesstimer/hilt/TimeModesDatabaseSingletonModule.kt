package com.example.chesstimer.hilt

import android.content.Context
import androidx.room.Room
import com.example.chesstimer.timeModesDatabase.TimeModesDAO
import com.example.chesstimer.timeModesDatabase.TimeModesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object TimeModesDatabaseSingletonModule {

    @Singleton
    @Provides
    fun provideTimeModesDatabase(@ApplicationContext appContext: Context): TimeModesDatabase {
        return Room.databaseBuilder(
            appContext,
            TimeModesDatabase::class.java,
            "Sample.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTimeModesDao(database: TimeModesDatabase): TimeModesDAO {
        return database.timeModesDao
    }
}