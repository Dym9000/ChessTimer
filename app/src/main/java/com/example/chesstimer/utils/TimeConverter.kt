package com.example.chesstimer.utils

class TimeConverter {
    fun convert(hour: Int?, minute: Int?, second: Int?): Long {
        return if (hour != null && minute != null && second != null)
            (hour * 3600 + minute * 60 + second).toLong()
        else throw Exception("Invalid argument passed to converter. Check if arguments were != null")
    }
}