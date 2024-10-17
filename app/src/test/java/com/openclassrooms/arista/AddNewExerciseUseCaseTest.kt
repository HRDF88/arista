package com.openclassrooms.arista

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import com.openclassrooms.arista.domain.usecase.AddNewExerciseUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import java.time.LocalDateTime
import java.time.Month

class AddNewExerciseUseCaseTest {
    private lateinit var addNewExerciseUseCase: AddNewExerciseUseCase
    private val exerciseRepository: ExerciseRepository = mock()

    @Before
    fun setup() {
        addNewExerciseUseCase = AddNewExerciseUseCase(exerciseRepository)
    }

    @Test
    fun `when execute is called, should call addExercise in exercise repository`() = runBlocking {
        // Arrange
        val exercise = Exercise(
            startTime = LocalDateTime.of(2024, Month.OCTOBER, 14, 14, 38),
            duration = 30,
            category = ExerciseCategory.Riding,
            intensity = 5
        )

        // Act
        addNewExerciseUseCase.execute(exercise)

        // Assert
        verify(exerciseRepository, times(1)).addExercise(exercise)
    }
}