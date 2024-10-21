package com.openclassrooms.arista.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.openclassrooms.arista.data.entity.SleepDto
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface for managing SleepDto data in the local database.
 *
 */
@Dao
interface SleepDtoDao {

    /**
     * Inserts a sleep entry into the database.
     *
     * @param sleep The sleep entry to insert, represented as a SleepDto object.
     * @return The ID of the inserted sleep entry.
     */
    @Insert
    suspend fun insertSleep(sleep: SleepDto): Long

    /**
     * Retrieves all sleep entries from the database.
     *
     * @return A Flow emitting a list of all sleep entries.
     */
    @Query("SELECT * FROM sleep")
    fun getAllSleep(): Flow<List<SleepDto>>

    /**
     * Deletes a sleep entry from the database by its ID.
     *
     * @param id The ID of the sleep entry to delete.
     */
    @Query("DELETE FROM sleep WHERE id = :id")
    suspend fun deleteSleepById(id: Long)
}