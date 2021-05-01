package com.example.chesstimer.addingNewTimeModeFragment.timeChanger

abstract class TimeChanger {

    abstract val minInt: Int
    abstract val maxInt: Int
    abstract val timeInterval: Int
    private var updatedTime: Int = 0

    fun add(currentTime: Int): Int {
        updatedTime = currentTime
        if (currentTime < maxInt) {
            updatedTime += timeInterval
        } else {
            updatedTime = minInt
        }
        return updatedTime
    }

    fun subtract(currentTime: Int): Int {
        updatedTime = currentTime
        if (currentTime > minInt) {
            updatedTime -= timeInterval
        } else {
            updatedTime = maxInt
        }
        return updatedTime
    }
}