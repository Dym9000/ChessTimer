package com.example.chesstimer.addingNewTimeModeFragment.timeChanger

class SecondChanger:TimeChanger() {
    override val minInt: Long
        get() = 0
    override val maxInt: Long
        get() = 50
    override val timeInterval: Int
        get() = 10
}