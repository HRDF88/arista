package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.SleepDtoDao
import com.openclassrooms.arista.domain.model.Sleep
import kotlinx.coroutines.flow.first

/**
 * The repository class for managing sleep data.
 *
 * @param sleepDao The data access object for SleepDto.
 */
class SleepRepository(private val sleepDao: SleepDtoDao) {

    /**
     * Retrieves all sleep data from the database.
     *
     * @return The list of all sleep records.
     */
    suspend fun getAllSleep(): List<Sleep> {
        return sleepDao.getAllSleep()
            .first() // Collect the first emission of the Flow
            .map { sleeepDto ->
                Sleep(
                    startTime = sleeepDto.startTime,
                    duration = sleeepDto.duration,
                    quality = sleeepDto.quality
                )
            }
    }
}

