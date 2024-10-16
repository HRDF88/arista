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
    @Insert
    suspend fun insertExercise(exercise: ExerciseDto): Long


    @Query(value = "SELECT * FROM exercise")
    fun getAllExercises(): Flow<List<ExerciseDto>>


    @Query(value = "DELETE FROM exercise WHERE id = :id")
    suspend fun deleteExerciseById(id: Long)



}