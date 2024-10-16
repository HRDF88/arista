package com.openclassrooms.arista.domain.model

/**
 * Data class representing a sleep record.
 *
 * @param startTime The start time of the sleep record in milliseconds.
 * @param duration The duration of the sleep in minutes.
 * @param quality The quality of the sleep on a scale from 1 to 5.
 */
data class Sleep(@JvmField var startTime: Long, var duration: Int, var quality: Int)
