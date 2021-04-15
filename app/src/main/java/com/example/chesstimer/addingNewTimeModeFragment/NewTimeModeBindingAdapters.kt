package com.example.chesstimer.addingNewTimeModeFragment

import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.chesstimer.R

@BindingAdapter("app:isSecondPlayer")
fun setNextPlayerButtonText(view:TextView, isSecondPlayer:Boolean?){
        when(isSecondPlayer){
            true -> view.setText(R.string.save_settings_btn)
            else -> view.setText(R.string.player_2_settings_btn)
    }
}

@BindingAdapter("app:isSecondPlayerTitle")
fun setTitlePlayerText(view:TextView, isSecondPlayer:Boolean?){
        when(isSecondPlayer){
            true -> view.setText(R.string.player_2_title)
            else -> view.setText(R.string.player_1_title)
        }
}

@BindingAdapter("app:currentTime")
fun setButtonEnabled(view: Button, currentTime:Long?){
        when (currentTime) {
            0L -> view.isEnabled = false
            else -> view.isEnabled = true
        }
}