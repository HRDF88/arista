package com.openclassrooms.arista.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class representing a sleep record in the database.
 *
 * @param id The id of the sleep record. Generated automatically.
 * @param startTime The start time of the sleep.
 * @param duration The duration of the sleep.
 * @param quality The quality of the sleep.
 */
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
