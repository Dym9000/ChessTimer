package com.example.chesstimer.timeModes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.chesstimer.timeModesDatabase.TimeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TimeModesViewModel @Inject constructor(private val repository: TimeModesRepository)
    :ViewModel() {

    val timeModes:LiveData<List<TimeMode>> = repository.loadTimeModes()

}