package com.openclassrooms.arista.domain.model

import com.openclassrooms.arista.data.entity.ExerciseDto
import java.time.LocalDateTime

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

}
