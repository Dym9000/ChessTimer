package com.example.chesstimer.displayTimeModesFragment

import androidx.lifecycle.LiveData
import com.example.chesstimer.timeModesDatabase.TimeModeWithPlayers
import com.example.chesstimer.timeModesDatabase.TimeModesDAO

class TimeModesRepository(private val timeModesDao: TimeModesDAO) {

    suspend fun insertTimeModesAndPlayers(times: List<Long>) {
        timeModesDao.insertAll(times)
    }

    fun loadTimeModes(): LiveData<List<TimeModeWithPlayers>> {
        return timeModesDao.getAllTimeModes()
    }

    suspend fun loadSingleTimeModeWithPlayers(id: Int): TimeModeWithPlayers {
        return timeModesDao.getSingleTimeMode(id)
    }

//    fun deleteTimeMode(timeMode:TimeMode){
//        timeModesDao.delete(timeMode)
//    }

//    fun clearTimeModesList{
//        timeModesDao.clear()
//    }

}