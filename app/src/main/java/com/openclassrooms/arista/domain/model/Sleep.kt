package com.openclassrooms.arista.domain.model

import com.openclassrooms.arista.data.entity.SleepDto

/**
 * Data class representing a sleep record.
 *
 * @param startTime The start time of the sleep record in milliseconds.
 * @param duration The duration of the sleep in minutes.
 * @param quality The quality of the sleep on a scale from 1 to 5.
 */
data class Sleep(@JvmField var startTime: Long, var duration: Int, var quality: Int) {

    companion object {
        /**
         * Creates a Sleep object from a SleepDto object.
         *
         * @param sleepDto The SleepDto object to convert.
         * @return A Sleep object with data mapped from the SleepDto object.
         */
        fun fromDto(sleepDto: SleepDto): Sleep {
            return Sleep(
                startTime = sleepDto.startTime,
                duration = sleepDto.duration,
                quality = sleepDto.quality
            )
        }
    }
}

