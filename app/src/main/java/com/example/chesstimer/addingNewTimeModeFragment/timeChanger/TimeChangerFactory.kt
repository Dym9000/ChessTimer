package com.example.chesstimer.addingNewTimeModeFragment.timeChanger

class TimeChangerFactory {
    fun createTimeChanger(type:TimeChangerTypes): TimeChanger?{
            return when(type){
                TimeChangerTypes.HOUR -> HourChanger()
                TimeChangerTypes.MINUTE -> MinuteChanger()
                TimeChangerTypes.SECOND -> SecondChanger()
                else -> throw Exception("Invalid time changer type")
            }
    }
}