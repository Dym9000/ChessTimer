package com.example.chesstimer.addingNewTimeModeFragment.timeChanger

abstract class TimeChanger() {

    abstract val minInt:Long
    abstract val maxInt:Long
    abstract val timeInterval:Int
    private var updatedTime:Long = 0L

    fun add(currentTime:Long): Long{
        updatedTime = currentTime
        if (currentTime < maxInt) {
            updatedTime += timeInterval
        } else {
            updatedTime = minInt
        }
        return updatedTime
    }

    fun subtract(currentTime:Long):Long{
        updatedTime = currentTime
        if (currentTime > minInt) {
            updatedTime -= timeInterval
        } else {
            updatedTime = maxInt
        }
        return updatedTime
    }
}