package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import javax.inject.Inject

/**
 * This use case class is responsible for deleting an Exercise.
 */
class DeleteExerciseUseCase @Inject constructor(private val exerciseRepository: ExerciseRepository) {
    /**
     * Executes the use case to delete an exercise.
     *
     * @param exercise The Exercise object to be deleted.
     */
    suspend fun execute(exercise: Exercise) {
        exerciseRepository.deleteExercise(exercise)
    }
}