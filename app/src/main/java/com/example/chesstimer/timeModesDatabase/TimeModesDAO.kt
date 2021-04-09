package com.example.chesstimer.timeModesDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TimeModesDAO {

    @Insert
    suspend fun insert(timeMode: TimeMode)

    @Delete
    suspend fun delete(timeMode: TimeMode)

    @Query("DELETE FROM time_modes_table")
    suspend fun clear()

    @Query("SELECT * FROM time_modes_table ORDER BY modeId ASC")
    fun getAllTimeModes():LiveData<List<TimeMode>>
}