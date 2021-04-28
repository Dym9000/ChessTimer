package com.example.chesstimer.addingNewTimeModeFragment.timeChanger

class HourChanger:TimeChanger() {
    override val minInt: Long
        get() = 0
    override val maxInt: Long
        get() = 99
    override val timeInterval: Int
        get() = 1
}