package com.openclassrooms.arista.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Sleep")
data class SleepDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,

    @ColumnInfo(name = "start_Time")
    var startTime: Long,

    @ColumnInfo(name = "Duration")
    var duration: Int,

    @ColumnInfo(name = "quality")
    var quality: Int
)
