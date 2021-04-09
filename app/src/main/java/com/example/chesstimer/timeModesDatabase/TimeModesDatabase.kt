package com.example.chesstimer.timeModesDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TimeMode::class], version = 1, exportSchema = false)
abstract class TimeModesDatabase : RoomDatabase() {

    abstract val timeModesDao: TimeModesDAO

//    companion object : SingletonHolder<TimeModesDatabase, Context>({
//        Room.databaseBuilder(
//            it.applicationContext,
//            TimeModesDatabase::class.java,
//            "Sample.db"
//        )
//            .fallbackToDestructiveMigration()
//            .build()
//    })
}