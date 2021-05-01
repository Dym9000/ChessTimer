package com.example.chesstimer.addingNewTimeModeFragment.timeChanger

class SecondChanger : TimeChanger() {
    override val minInt: Int
        get() = 0
    override val maxInt: Int
        get() = 50
    override val timeInterval: Int
        get() = 10
}