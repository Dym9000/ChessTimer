package com.example.chesstimer.displayTimeModesFragment

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.chesstimer.timeModesDatabase.Player
import com.example.chesstimer.timeModesDatabase.TimeModeWithPlayers
import java.util.*

@BindingAdapter("app:timeMode")
fun TextView.formatTimeMode(timeModeItem: TimeModeWithPlayers?) {
    timeModeItem?.let {
        text = timeModeFormatter(timeModeItem)
    }
}

private fun timeModeFormatter(timeMode: TimeModeWithPlayers): String {
    val result = StringBuilder()
    val playersList: List<Player> = timeMode.players
    playersList.let {
        for (item in playersList) {
            val hours: Int = item.timeInSeconds.toInt() / 3600
            val minutes: Int = (item.timeInSeconds.toInt() % 3600) / 60
            val seconds: Int = (item.timeInSeconds.toInt() % 60)

            val time = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds)
            result.append(time)
            result.append("       ")
        }
    }
    return result.toString().trim()
}