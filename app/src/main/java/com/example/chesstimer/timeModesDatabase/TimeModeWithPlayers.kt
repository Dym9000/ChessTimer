package com.example.chesstimer.timeModesDatabase

import androidx.room.Embedded
import androidx.room.Relation

data class TimeModeWithPlayers(

    @Embedded
    val singleTimeMode: TimeMode,
    @Relation(
        parentColumn = "modeId",
        entityColumn = "timeModeId"
    )
    val players: List<Player> = emptyList()
)