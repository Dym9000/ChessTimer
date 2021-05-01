package com.example.chesstimer.addingNewTimeModeFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chesstimer.addingNewTimeModeFragment.timeChanger.TimeChangerFactory
import com.example.chesstimer.addingNewTimeModeFragment.timeChanger.TimeChangerTypes
import com.example.chesstimer.displayTimeModesFragment.TimeModesRepository
import com.example.chesstimer.utils.TimeConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTimeModeViewModel @Inject constructor(
    private val repository: TimeModesRepository,
    private val converter: TimeConverter,
    private val timeChangerFactory: TimeChangerFactory
) : ViewModel() {

    private var firstPlayerTimeInSeconds: Long? = 0L
    private var secondPlayerTimeInSeconds: Long? = 0L

    private val hourChanger = timeChangerFactory.createTimeChanger(TimeChangerTypes.HOUR)
    private val minuteChanger = timeChangerFactory.createTimeChanger(TimeChangerTypes.MINUTE)
    private val secondChanger = timeChangerFactory.createTimeChanger(TimeChangerTypes.SECOND)

    private var _currentTimeInSeconds = MutableLiveData<Long>()
    val currentTimeInSeconds: LiveData<Long>
        get() {
            return _currentTimeInSeconds
        }

    private var _navigatedToSecondPlayer = MutableLiveData<Boolean?>()
    val navigatedToSecondPlayer: LiveData<Boolean?>
        get() {
            return _navigatedToSecondPlayer
        }

    private var _hours = MutableLiveData<Int>()
    val hours: LiveData<Int>
        get() {
            return _hours
        }

    private var _minutes = MutableLiveData<Int>()
    val minutes: LiveData<Int>
        get() {
            return _minutes
        }

    private var _seconds = MutableLiveData<Int>()
    val seconds: LiveData<Int>
        get() {
            return _seconds
        }

    init {
        _hours.value = 0
        _minutes.value = 0
        _seconds.value = 0
        _currentTimeInSeconds.value = 0L
    }

    fun onAddHourClick() {
        _hours.value = hours.value?.let { hourChanger?.add(it) }
        convert()
    }

    fun onSubtractHourClick() {
        _hours.value = hours.value?.let { hourChanger?.subtract(it) }
        convert()
    }

    fun onAddMinuteClick() {
        _minutes.value = minutes.value?.let { minuteChanger?.add(it) }
        convert()
    }

    fun onSubtractMinuteClick() {
        _minutes.value = minutes.value?.let { minuteChanger?.subtract(it) }
        convert()
    }

    fun onAddSecondClick() {
        _seconds.value = seconds.value?.let { secondChanger?.add(it) }
        convert()
    }

    fun onSubtractSecondClick() {
        _seconds.value = seconds.value?.let { secondChanger?.subtract(it) }
        convert()
    }

    private fun convert() {
        _currentTimeInSeconds.value =
            converter.convert(_hours.value, _minutes.value, _seconds.value)
    }

    fun onNextPlayerClick() {
        if (navigatedToSecondPlayer.value != true) {
            firstPlayerTimeInSeconds = currentTimeInSeconds.value
            _navigatedToSecondPlayer.value = true
        } else {
            secondPlayerTimeInSeconds = currentTimeInSeconds.value
            val times = mutableListOf<Long>()
            firstPlayerTimeInSeconds?.let { times.add(it) }
            secondPlayerTimeInSeconds?.let { times.add(it) }

            viewModelScope.launch {
                repository.insertTimeModesAndPlayers(times)
                _navigatedToSecondPlayer.value = false
            }
        }
    }

}