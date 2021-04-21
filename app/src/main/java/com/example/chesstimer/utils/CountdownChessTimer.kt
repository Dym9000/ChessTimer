package com.example.chesstimer.utils

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class CountdownChessTimer {

    private val mCountInterval: Long = 1000L
    private val mGameOver: Long = 0L

    private var mIsCountdownRunning: Boolean = false

    lateinit var mCountdownTimer: CountDownTimer

    private var _time1 = MutableLiveData<Long>()
    val time1: LiveData<Long>
        get() {
            return _time1
        }

    private var _time2 = MutableLiveData<Long>()
    val time2: LiveData<Long>
        get() {
            return _time2
        }

    fun startTimer(player: Int, timeLeftInMillis: Long?) {
        stopTimer()
        mIsCountdownRunning = true
        timeLeftInMillis?.let {
            mCountdownTimer = object : CountDownTimer(timeLeftInMillis, mCountInterval) {
                override fun onTick(millisecUntilFinished: Long) {
                    when (player) {
                        0 -> _time1.value = millisecUntilFinished
                        1 -> _time2.value = millisecUntilFinished
                    }
                }

                override fun onFinish() {
                    when (player) {
                        0 -> _time1.value = mGameOver
                        1 -> _time2.value = mGameOver
                    }
                }
            }
                .start()
        }
    }

    private fun stopTimer() {
        if (mIsCountdownRunning) {
            mCountdownTimer.cancel()
        }
    }

    fun setStartingTime(time1: Long?, time2: Long?) {
        _time1.value = time1
        _time2.value = time2
    }


}