package com.openclassrooms.arista.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.openclassrooms.arista.domain.model.ExerciseCategory
import java.time.LocalDateTime

/**
 * Data class representing an exercise record in the database.
 *
 * @param id The id of the exercise record. Generated automatically.
 * @param startTime The start time of the exercise.
 * @param duration The duration of the exercise.
 * @param category The category of the exercise.
 * @param intensity The intensity of the exercise.
 */
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