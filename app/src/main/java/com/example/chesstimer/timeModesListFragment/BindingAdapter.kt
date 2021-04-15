package com.example.chesstimer.timeModesListFragment

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.chesstimer.timeModesDatabase.Player
import com.example.chesstimer.timeModesDatabase.TimeModeWithPlayers

@BindingAdapter("app:timeMode")
fun TextView.formatTimeMode(timeModeItem: TimeModeWithPlayers?){
    timeModeItem?.let {
        text = timeModeFormatter(timeModeItem)
    }
}

private fun timeModeFormatter(timeMode: TimeModeWithPlayers): String {
    val result = StringBuilder()
    val playersList:List<Player> = timeMode.players
    playersList.let {
        for (item in playersList){
            val hours: Int = item.timeInSeconds.toInt()/3600
            val minutes: Int = (item.timeInSeconds.toInt() %  3600) / 60
            val seconds: Int = (item.timeInSeconds.toInt() % 60)
            if (hours<10) result.append("0")
            result.append("$hours:")
            if (minutes<10) result.append("0")
            result.append("$minutes:")
            if (seconds<10) result.append("0")
            result.append("$seconds")
            result.append("       ")
        }
    }
    return result.toString().trim()
}