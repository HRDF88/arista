package com.openclassrooms.arista.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.openclassrooms.arista.domain.model.ExerciseCategory
import java.time.LocalDateTime

@Entity(tableName = "exercise")
data class ExerciseDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = 0,


    @ColumnInfo(name = "start_time")
    var startTime: LocalDateTime,


    @ColumnInfo(name = "duration")
    var duration: Int,


    @ColumnInfo(name = "category")
    var category: ExerciseCategory,


    @ColumnInfo(name = "intensity")
    var intensity: Int
)