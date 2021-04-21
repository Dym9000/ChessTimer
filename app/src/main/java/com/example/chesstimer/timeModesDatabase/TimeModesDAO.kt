package com.example.chesstimer.timeModesDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
abstract class TimeModesDAO {

    private suspend fun createAndInsertPlayers(id: Long, times: List<Long>) {
        val players = mutableListOf<Player>()
        for (time in times) {
            players.add(Player(timeModeId = id, timeInSeconds = time))
        }
        insertPlayers(players)
    }

    @Insert
    abstract suspend fun insertTimeMode(timeMode: TimeMode): Long

    @Insert
    abstract suspend fun insertPlayers(players: List<Player>)
//    @Delete
//    suspend fun delete(timeMode: TimeMode)
//
//    @Query("DELETE FROM TimeMode")
//    suspend fun clear()

    @Transaction
    @Query("SELECT * FROM TimeMode WHERE modeId = :id")
    abstract suspend fun getSingleTimeMode(id: Int): TimeModeWithPlayers

    @Transaction
    open suspend fun insertAll(times: List<Long>) {
        val id = insertTimeMode(timeMode = TimeMode())
        createAndInsertPlayers(id, times)
    }

    @Transaction
    @Query("SELECT * FROM TimeMode")
    abstract fun getAllTimeModes(): LiveData<List<TimeModeWithPlayers>>
}