package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.ExerciseDtoDao
import com.openclassrooms.arista.domain.model.Exercise
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ExerciseRepository @Inject constructor(private val exerciseDao: ExerciseDtoDao) {


    // Get all exercises
    suspend fun getAllExercises(): List<Exercise> {
        return exerciseDao.getAllExercises()
            .first() // Collect the first emission of the Flow
            .map { exerciseDto ->
                Exercise(
                    id = exerciseDto.id,
                    startTime = exerciseDto.startTime,
                    duration = exerciseDto.duration,
                    category = exerciseDto.category,
                    intensity = exerciseDto.intensity
                )
            }
    }


    // Add a new exercise
    suspend fun addExercise(exercise: Exercise) {
        exerciseDao.insertExercise(exercise.toDto())
    }


    // Delete an exercise
    suspend fun deleteExercise(exercise: Exercise) {
        // If there is no id, you can raise an exception and catch it in the use case and viewmodel
        exercise.id?.let {
            exerciseDao.deleteExerciseById(
                id = exercise.id,
            )
        }
    }
}