package com.example.chesstimer.displayTimeModesFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chesstimer.timeModesDatabase.TimeMode
import com.example.chesstimer.timeModesDatabase.TimeModeWithPlayers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

    fun onSwipe(item: TimeModeWithPlayers) {
        viewModelScope.launch {
            repository.deleteSingleTimeModeWithPlayers(item.singleTimeMode, item.players)
        }
    }

    fun onClearDataIconClicked(){
        viewModelScope.launch {
            repository.clearTimeModesList()
        }
    }

    var myList = (1..20).toList().filter { true }
}