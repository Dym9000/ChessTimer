package com.example.chesstimer.timeModesListFragment

import androidx.lifecycle.LiveData
import com.example.chesstimer.timeModesDatabase.TimeModeWithPlayers
import com.example.chesstimer.timeModesDatabase.TimeModesDAO

class TimeModesRepository (private val timeModesDao: TimeModesDAO) {

    suspend fun insertTimeModesAndPlayers(times:List<Long>){
        timeModesDao.insertAll(times)
    }

    fun loadTimeModes():LiveData<List<TimeModeWithPlayers>>{
        return timeModesDao.getAllTimeModes()
    }

//    fun deleteTimeMode(timeMode:TimeMode){
//        timeModesDao.delete(timeMode)
//    }

//    fun clearTimeModesList{
//        timeModesDao.clear()
//    }

}