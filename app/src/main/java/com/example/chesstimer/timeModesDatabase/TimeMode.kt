package com.example.chesstimer.timeModesDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TimeMode(
    @PrimaryKey(autoGenerate = true)
    val modeId: Int = 0
)
