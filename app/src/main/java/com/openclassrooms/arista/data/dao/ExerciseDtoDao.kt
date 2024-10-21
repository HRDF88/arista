package com.openclassrooms.arista.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.openclassrooms.arista.data.entity.ExerciseDto
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface for managing ExerciseDto data in the local database.
 *
 */
@Dao
interface ExerciseDtoDao {

    /**
     * Inserts an exercise entry into the database.
     *
     * @param exercise The exercise entry to insert, represented as an ExerciseDto object.
     * @return The ID of the inserted exercise entry.
     */
    @Insert
    suspend fun insertExercise(exercise: ExerciseDto): Long

    /**
     * Retrieves all exercise entries from the database.
     *
     * @return A Flow emitting a list of all exercise entries.
     */
    @Query(value = "SELECT * FROM exercise")
    fun getAllExercises(): Flow<List<ExerciseDto>>

    /**
     * Deletes an exercise entry from the database by its ID.
     *
     * @param id The ID of the exercise entry to delete.
     */
    @Query(value = "DELETE FROM exercise WHERE id = :id")
    suspend fun deleteExerciseById(id: Long)


}