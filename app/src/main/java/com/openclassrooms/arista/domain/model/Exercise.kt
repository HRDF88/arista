package com.openclassrooms.arista.domain.model

import com.openclassrooms.arista.data.entity.ExerciseDto
import java.time.LocalDateTime

/**
 * Data class representing an exercise.
 *
 * @property id The unique identifier of the exercise.
 * @property startTime The start time of the exercise.
 * @property duration The duration of the exercise in minutes.
 * @property category The category of the exercise.
 * @property intensity The intensity level of the exercise.
 */
data class Exercise(
    val id: Long? = null,
    var startTime: LocalDateTime,
    var duration: Int,
    var category: ExerciseCategory,
    var intensity: Int,
) {

    fun toDto(): ExerciseDto {
        return ExerciseDto(
            id = this.id,
            startTime = this.startTime,
            duration = this.duration,
            category = this.category,
            intensity = this.intensity
        )
    }
    companion object {
        /**
         * Creates a Exercise object from a ExerciseDto object.
         *
         * @param exerciseDto The SleepDto object to convert.
         * @return A Exercise object with data mapped from the ExerciseDto object.
         */
        fun fromDto(exerciseDto: ExerciseDto): Exercise {
            return Exercise(
                id = exerciseDto.id,
                startTime = exerciseDto.startTime,
                duration = exerciseDto.duration,
                category = exerciseDto.category,
                intensity = exerciseDto.intensity
            )
        }
    }
}
