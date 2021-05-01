package com.example.chesstimer.addingNewTimeModeFragment.timeChanger

class HourChanger : TimeChanger() {
    override val minInt: Int
        get() = 0
    override val maxInt: Int
        get() = 99
    override val timeInterval: Int
        get() = 1
}