package com.example.chesstimer.gameplayFragment

import android.text.format.DateUtils
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.example.chesstimer.R
import java.util.concurrent.TimeUnit

@BindingAdapter("app:currentTime")
fun formatTime(view: TextView, timeInMillis: Long?) {
    timeInMillis?.let {
        if(timeInMillis == 0L){
            view.text = view.context.getString(R.string.game_over_message)
        }else{
            val timeInSec = TimeUnit.MILLISECONDS.toSeconds(timeInMillis)
            val timeFormatted = DateUtils.formatElapsedTime(timeInSec)
            view.text = timeFormatted
        }
    }
}

@BindingAdapter("app:isGameStarted")
fun setViewGone(view: View, isGameStarted: Boolean?) {
    when (isGameStarted) {
        true -> view.isVisible = true
        else -> view.isVisible = false
    }
}