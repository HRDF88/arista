package com.openclassrooms.arista.data

import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import com.openclassrooms.arista.domain.model.Sleep
import com.openclassrooms.arista.domain.model.User
import org.threeten.bp.LocalDateTime
import java.util.Arrays
import java.util.Optional

class FakeApiService {

    // Static data
    var user: User = User("John Doe", "johndoe@example.com", "password123")

    private val sleepData = listOf(
        Sleep(LocalDateTime.now().minusDays(1), 7, 8),
        Sleep(LocalDateTime.now().minusDays(2), 6, 5),
        Sleep(LocalDateTime.now().minusDays(3), 8, 9)
    )

    private val exerciseData = mutableListOf(
        Exercise(LocalDateTime.now().minusHours(5), 30, ExerciseCategory.Running, 7),
        Exercise(LocalDateTime.now().minusDays(1).minusHours(3), 45, ExerciseCategory.Natation, 6),
        Exercise(LocalDateTime.now().minusDays(2).minusHours(4), 60, ExerciseCategory.Football, 8)
    )

    // CRUD for Sleep
    fun getAllSleeps() = sleepData.toList()

    fun findSleepByStartTime(startTime: LocalDateTime) = sleepData.find { it.startTime == startTime }

    // CRUD for Exercise
    fun getAllExercises() = exerciseData.toList()

    fun addExercise(exercise: Exercise) {
        exerciseData.add(exercise)
    }

    fun deleteExercise(exercise: Exercise) {
        exerciseData.remove(exercise)
    }
}