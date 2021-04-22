package com.example.chesstimer.timeModesDatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
abstract class TimeModesDAO {

    private suspend fun createAndInsertPlayers(id: Long, times: List<Long>) {
        val players = mutableListOf<Player>()
        for (time in times) {
            players.add(Player(timeModeId = id, timeInSeconds = time))
        }
        insertPlayers(players)
    }

    @Transaction
    open suspend fun insertAll(times: List<Long>) {
        val id = insertTimeMode(timeMode = TimeMode())
        createAndInsertPlayers(id, times)
    }

    @Insert
    abstract suspend fun insertTimeMode(timeMode: TimeMode): Long

    @Insert
    abstract suspend fun insertPlayers(players: List<Player>)

    @Transaction
    open suspend fun deleteSingleTimeModeWithPlayers(timeMode: TimeMode, players: List<Player>) {
        deleteTimeMode(timeMode)
        deletePlayers(players)
    }

    @Delete
    abstract suspend fun deleteTimeMode(timeMode: TimeMode)

    @Delete
    abstract suspend fun deletePlayers(players: List<Player>)

    @Transaction
    open suspend fun clearAllData() {
        clearAllTimeModes()
        clearAllPlayers()
    }

    @Query("DELETE FROM TimeMode")
    abstract suspend fun clearAllTimeModes()

    @Query("DELETE FROM Player")
    abstract suspend fun clearAllPlayers()

    @Transaction
    @Query("SELECT * FROM TimeMode WHERE modeId = :id")
    abstract suspend fun getSingleTimeMode(id: Int): TimeModeWithPlayers

    @Transaction
    @Query("SELECT * FROM TimeMode")
    abstract fun getAllTimeModes(): LiveData<List<TimeModeWithPlayers>>
}