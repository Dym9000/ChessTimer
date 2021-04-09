package com.example.chesstimer.timeModes

import androidx.lifecycle.LiveData
import com.example.chesstimer.timeModesDatabase.TimeMode
import com.example.chesstimer.timeModesDatabase.TimeModesDAO

class TimeModesRepository (private val timeModesDao: TimeModesDAO) {

//    fun insertTimeMode(timeMode:TimeMode){
//        timeModesDao.insert(timeMode)
//    }

    fun loadTimeModes():LiveData<List<TimeMode>>{
        return timeModesDao.getAllTimeModes()
    }

//    fun deleteTimeMode(timeMode:TimeMode){
//        timeModesDao.delete(timeMode)
//    }

//    fun clearTimeModesList{
//        timeModesDao.clear()
//    }

}