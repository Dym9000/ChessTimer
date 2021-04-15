package com.example.chesstimer.timeModesListFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.chesstimer.timeModesDatabase.TimeModeWithPlayers
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TimeModesViewModel @Inject constructor(private val repository: TimeModesRepository)
    :ViewModel() {

    val timeModes:LiveData<List<TimeModeWithPlayers>> = repository.loadTimeModes()

}