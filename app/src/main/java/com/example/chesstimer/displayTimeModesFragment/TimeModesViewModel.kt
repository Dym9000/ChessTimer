package com.example.chesstimer.displayTimeModesFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chesstimer.timeModesDatabase.TimeMode
import com.example.chesstimer.timeModesDatabase.TimeModeWithPlayers
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TimeModesViewModel @Inject constructor(private val repository: TimeModesRepository) :
    ViewModel() {

    val timeModes: LiveData<List<TimeModeWithPlayers>> = repository.loadTimeModes()

    private var _clickedItemId = MutableLiveData<Int>()
    val clickedItemId: LiveData<Int>
        get() {
            return _clickedItemId
        }

    fun onItemClicked(timeMode: TimeMode) {
        _clickedItemId.value = timeMode.modeId
    }

    fun navigatedToItemClicked() {
        _clickedItemId.value = null
    }
}