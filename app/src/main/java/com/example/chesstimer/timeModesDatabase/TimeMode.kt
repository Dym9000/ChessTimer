package com.example.chesstimer.timeModesDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "time_modes_table")
data class TimeMode(

    @PrimaryKey(autoGenerate = true)
    var modeId: Long = 0L,

    @ColumnInfo(name = "player1_hours")
    val player1_hours: Long? = null,

    @ColumnInfo(name = "player1_minutes")
    val player1_minutes: Long? = null,

    @ColumnInfo(name = "player1_seconds")
    val player1_seconds: Long? = null,

    @ColumnInfo(name = "player2_hours")
    val player2_hours: Long? = null,

    @ColumnInfo(name = "player2_minutes")
    val player2_minutes: Long? = null,

    @ColumnInfo(name = "player2_seconds")
    val player2_seconds: Long? = null
    )
