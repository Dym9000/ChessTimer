package com.example.chesstimer.timeModes

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.chesstimer.timeModesDatabase.TimeMode

@BindingAdapter(value=["timeMode","playerNumber"], requireAll = true)
fun formatTimeMode(textView:TextView, timeMode:TimeMode?, player:Int){
    timeMode?.let {
        textView.text = timeModeFormatter(timeMode, player)
    }
}

private fun timeModeFormatter(timeMode: TimeMode, player: Int): String {
    val colon = ":"
    if(player==1){
        return StringBuilder()
            .append(
                if (timeMode.player1_hours != null && timeMode.player1_hours > 0)
                    timeMode.player1_hours.toString() + colon else null
            )
            .append(
                if (timeMode.player1_minutes != null && timeMode.player1_minutes > 0)
                    timeMode.player1_minutes.toString() + colon else null
            )
            .append(
                if (timeMode.player1_seconds != null && timeMode.player1_seconds > 0)
                    timeMode.player1_seconds.toString() else null
            )
            .toString()
    }
    else {
        return StringBuilder()
            .append(
                if (timeMode.player2_hours != null && timeMode.player2_hours > 0)
                    timeMode.player2_hours.toString() + colon else null
            )
            .append(
                if (timeMode.player2_minutes != null && timeMode.player2_minutes > 0)
                    timeMode.player2_minutes.toString() + colon else null
            )
            .append(
                if (timeMode.player2_seconds != null && timeMode.player2_seconds > 0)
                    timeMode.player2_seconds.toString() else null
            ).toString()
    }
}