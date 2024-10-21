package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.data.repositoryInterface.ExerciseRepositoryInterface
import com.openclassrooms.arista.domain.model.Exercise
import javax.inject.Inject

/**
 * This use case class is responsible for retrieving all Exercises.
 */
class GetAllExercisesUseCase @Inject constructor(private val exerciseRepositoryInterface: ExerciseRepositoryInterface) {
    /**
     * Executes the use case to retrieve all exercises.
     *
     * @return The list of all exercises.
     */
    suspend fun execute(): List<Exercise> {
        return exerciseRepositoryInterface.getAllExercises()
    }
}