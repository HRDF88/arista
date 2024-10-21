package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.SleepDtoDao
import com.openclassrooms.arista.data.repositoryInterface.SleepRepositoryInterface
import com.openclassrooms.arista.domain.model.Sleep
import kotlinx.coroutines.flow.first

/**
 * The repository class for managing sleep data.
 *
 * @param sleepDao The data access object for SleepDto.
 */
class SleepRepository(private val sleepDao: SleepDtoDao) : SleepRepositoryInterface {

    /**
     * Retrieves all sleep data from the database.
     *
     * @return The list of all sleep records.
     */
    override suspend fun getAllSleep(): List<Sleep> {
        return sleepDao.getAllSleep()
            .first() // Collect the first emission of the Flow
            .map { sleepDto ->
                Sleep.fromDto(sleepDto)
            }
    }
}

