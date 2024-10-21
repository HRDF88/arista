package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.data.repositoryInterface.ExerciseRepositoryInterface
import com.openclassrooms.arista.domain.model.Exercise
import javax.inject.Inject

/**
 * This use case class is responsible for adding a new Exercise.
 */
class AddNewExerciseUseCase @Inject constructor(private val exerciseRepositoryInterface: ExerciseRepositoryInterface) {
    /**
     * Executes the use case to add a new exercise.
     *
     * @param exercise The Exercise object to be added.
     */
    suspend fun execute(exercise: Exercise) {
        exerciseRepositoryInterface.addExercise(exercise)
    }
}