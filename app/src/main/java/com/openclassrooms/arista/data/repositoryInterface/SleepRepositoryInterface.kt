package com.openclassrooms.arista.data.repositoryInterface

import com.openclassrooms.arista.domain.model.Sleep

/**
 * Interface for managing sleep data.
 */
interface SleepRepositoryInterface {
    /**
     * Gets all sleep data.
     *
     * @return The list of Sleep objects.
     */
    suspend fun getAllSleep(): List<Sleep>
}