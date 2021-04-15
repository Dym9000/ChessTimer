package com.example.chesstimer.utils

class TimeConverter{
    fun convert(hour:Int, minute:Int, second:Int):Long{
        return (hour*3600+minute*60+second).toLong()
    }
}