package com.example.chesstimer.timeModesDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TimeMode::class, Player::class], version = 2, exportSchema = false)
abstract class TimeModesDatabase : RoomDatabase() {

    abstract val timeModesDao: TimeModesDAO
}