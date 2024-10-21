package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.repositoryInterface.ExerciseRepositoryInterface
import com.openclassrooms.arista.data.dao.ExerciseDtoDao
import com.openclassrooms.arista.domain.model.Exercise
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/**
 * The repository class for managing exercise data.
 *
 * @param exerciseDao The data access object for ExerciseDto.
 */
class ExerciseRepository @Inject constructor(private val exerciseDao: ExerciseDtoDao) : ExerciseRepositoryInterface {


    /**
     * Retrieves all exercises from the database.
     *
     * @return The list of all exercises.
     */
    override suspend fun getAllExercises(): List<Exercise> {
        return exerciseDao.getAllExercises()
            .first() // Collect the first emission of the Flow
            .map { exerciseDto ->
                Exercise.fromDto(exerciseDto)
            }
    }

    /**
     * Adds a new exercise to the database.
     *
     * @param exercise The exercise to be added.
     */
    override suspend fun addExercise(exercise: Exercise) {
        exerciseDao.insertExercise(exercise.toDto())
    }

    /**
     * Deletes an exercise from the database.
     *
     * @param exercise The exercise to be deleted.
     */
    override suspend fun deleteExercise(exercise: Exercise) {
        // If there is no id, you can raise an exception and catch it in the use case and viewmodel
        exercise.id?.let {
            exerciseDao.deleteExerciseById(
                id = exercise.id,
            )
        }
    }
}