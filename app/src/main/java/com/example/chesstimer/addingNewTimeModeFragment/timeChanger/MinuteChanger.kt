package com.example.chesstimer.addingNewTimeModeFragment.timeChanger

class MinuteChanger: TimeChanger() {
    override val minInt: Long
        get() = 0
    override val maxInt: Long
        get() = 59
    override val timeInterval: Int
        get() = 1
}