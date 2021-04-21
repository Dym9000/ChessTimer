package com.example.chesstimer.gameplayFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chesstimer.displayTimeModesFragment.TimeModesRepository
import com.example.chesstimer.timeModesDatabase.TimeModeWithPlayers
import com.example.chesstimer.utils.CountdownChessTimer
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class GameplayViewModel(
    private val timeModeKey: Int,
    private val repository: TimeModesRepository,
    private val countdownChessTimer: CountdownChessTimer
) : ViewModel() {

    private val _timeMode = MutableLiveData<TimeModeWithPlayers>()

    private var _timeLeftInMillisPlayer1: Long? = 0L
    private var _timeLeftInMillisPlayer2: Long? = 0L

    val timeLeftInMillisPlayer1: LiveData<Long> = countdownChessTimer.time1
    val timeLeftInMillisPlayer2: LiveData<Long> = countdownChessTimer.time2

    private var _isGameStarted = MutableLiveData<Boolean>()
    val isGameStarted: LiveData<Boolean>
        get() {
            return _isGameStarted
        }

    private var _isTimer1Running = MutableLiveData<Boolean>()
    val isTimer1Running: LiveData<Boolean>
        get() {
            return _isTimer1Running
        }

    private var _isTimer2Running = MutableLiveData<Boolean>()
    val isTimer2Running: LiveData<Boolean>
        get() {
            return _isTimer2Running
        }

    init {
        viewModelScope.launch {
            _timeMode.value = initializeTimeMode()
            initializeStartTimes()
            setStartTime()
        }
    }

    private suspend fun initializeTimeMode(): TimeModeWithPlayers {
        return repository.loadSingleTimeModeWithPlayers(timeModeKey)
    }

    private fun initializeStartTimes() {
        val time1 = _timeMode.value?.players?.get(PLAYER_1_INDEX)?.timeInSeconds
        val time2 = _timeMode.value?.players?.get(PLAYER_2_INDEX)?.timeInSeconds
        _timeLeftInMillisPlayer1 = time1?.let { TimeUnit.SECONDS.toMillis(it) }
        _timeLeftInMillisPlayer2 = time2?.let { TimeUnit.SECONDS.toMillis(it) }
    }

    private fun setStartTime() {
        countdownChessTimer.setStartingTime(_timeLeftInMillisPlayer1, _timeLeftInMillisPlayer2)
    }

    fun onSwapTimesClick() {
        val timer1 = _timeLeftInMillisPlayer1
        _timeLeftInMillisPlayer1 = _timeLeftInMillisPlayer2
        _timeLeftInMillisPlayer2 = timer1
        setStartTime()
    }

    fun onTime1Changed(time: Long) {
        _timeLeftInMillisPlayer1 = time
    }

    fun onTime2Changed(time: Long) {
        _timeLeftInMillisPlayer2 = time
    }

    fun onPlayer1Click() {
        countdownChessTimer.startTimer(PLAYER_2_INDEX, timeLeftInMillisPlayer2.value)
        _isTimer1Running.value = false
        _isTimer2Running.value = true
    }

    fun onPlayer2Click() {
        countdownChessTimer.startTimer(PLAYER_1_INDEX, timeLeftInMillisPlayer1.value)
        _isTimer1Running.value = true
        _isTimer2Running.value = false
    }

    fun onStartButtonCLick(player: Int) {
        _isGameStarted.value = true
        when (player) {
            0 -> onPlayer1Click()
            1 -> onPlayer2Click()
        }
    }

    companion object {
        const val PLAYER_1_INDEX = 0
        const val PLAYER_2_INDEX = 1
    }
}