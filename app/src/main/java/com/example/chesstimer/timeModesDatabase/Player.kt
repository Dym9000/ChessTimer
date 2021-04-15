package com.example.chesstimer.timeModesDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Player (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val timeModeId: Long,
    val timeInSeconds:Long
    ) {
}
