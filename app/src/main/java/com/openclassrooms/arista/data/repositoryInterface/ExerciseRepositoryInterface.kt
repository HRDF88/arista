package com.openclassrooms.arista.data.repositoryInterface

import com.openclassrooms.arista.domain.model.Exercise

/**
 * Interface for managing exercise data.
 */
interface ExerciseRepositoryInterface {

    /**
     * Gets all exercises.
     *
     * @return The list of Exercise objects.
     */
    suspend fun getAllExercises(): List<Exercise>

    /**
     * Adds an exercise.
     *
     * @param exercise The exercise to add.
     */
    suspend fun addExercise(exercise: Exercise)

    /**
     * Deletes an exercise.
     *
     * @param exercise The exercise to delete.
     */
    suspend fun deleteExercise(exercise: Exercise)
}