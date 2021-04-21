package com.example.chesstimer.addingNewTimeModeFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chesstimer.displayTimeModesFragment.TimeModesRepository
import com.example.chesstimer.utils.TimeConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTimeModeViewModel @Inject constructor(
    private val repository: TimeModesRepository,
    private val converter: TimeConverter
) : ViewModel() {

    private val minValue = 0
    private val maxHour = 99
    private val maxMin = 59
    private val maxSec = 50
    private val secondInterval = 10
    private val hourAndMinInterval = 1

    private var firstPlayerTimeInSeconds: Long = 0
    private var secondPlayerTimeInSeconds: Long = 0

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
        add(hours, _hours, maxHour, hourAndMinInterval)
    }

    fun onSubtractHourClick() {
        subtract(hours, _hours, maxHour, hourAndMinInterval)
    }

    fun onAddMinuteClick() {
        add(minutes, _minutes, maxMin, hourAndMinInterval)
    }

    fun onSubtractMinuteClick() {
        subtract(minutes, _minutes, maxMin, hourAndMinInterval)
    }

    fun onAddSecondClick() {
        add(seconds, _seconds, maxSec, secondInterval)
    }

    fun onSubtractSecondClick() {
        subtract(seconds, _seconds, maxSec, secondInterval)
    }

    private fun subtract(
        currentTime: LiveData<Int>,
        _varName: MutableLiveData<Int>,
        max: Int,
        interval: Int
    ) {
        if (currentTime.value!! > minValue) {
            _varName.value = _varName.value!!.minus(interval)
        } else {
            _varName.value = max
        }
        _currentTimeInSeconds.value =
            converter.convert(hours.value!!, minutes.value!!, seconds.value!!)
    }

    private fun add(
        currentTime: LiveData<Int>,
        _varName: MutableLiveData<Int>,
        max: Int,
        interval: Int
    ) {
        if (currentTime.value!! < max) {
            _varName.value = _varName.value!!.plus(interval)
        } else {
            _varName.value = minValue
        }
        _currentTimeInSeconds.value =
            converter.convert(hours.value!!, minutes.value!!, seconds.value!!)
    }

    fun onNextPlayerClick() {
        if (navigatedToSecondPlayer.value != true) {
            firstPlayerTimeInSeconds = currentTimeInSeconds.value!!
            _navigatedToSecondPlayer.value = true
        } else {
            secondPlayerTimeInSeconds = currentTimeInSeconds.value!!
            val times = mutableListOf<Long>()
            times.add(firstPlayerTimeInSeconds)
            times.add(secondPlayerTimeInSeconds)

            viewModelScope.launch {
                repository.insertTimeModesAndPlayers(times)
            }
            _navigatedToSecondPlayer.value = false
        }
    }

}