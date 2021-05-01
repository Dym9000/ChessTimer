package com.example.chesstimer.addingNewTimeModeFragment.timeChanger

class MinuteChanger : TimeChanger() {
    override val minInt: Int
        get() = 0
    override val maxInt: Int
        get() = 59
    override val timeInterval: Int
        get() = 1
}