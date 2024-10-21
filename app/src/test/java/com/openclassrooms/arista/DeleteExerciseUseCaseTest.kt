package com.openclassrooms.arista

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import com.openclassrooms.arista.domain.usecase.DeleteExerciseUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import java.time.LocalDateTime
import java.time.Month

class DeleteExerciseUseCaseTest {
    private lateinit var deleteExerciseUseCase: DeleteExerciseUseCase
    private val exerciseRepository: ExerciseRepository = mock()

    @Before
    fun setup() {
        deleteExerciseUseCase = DeleteExerciseUseCase(exerciseRepository)
    }

    @Test
    fun `when execute is called, should call deleteExercise in exercise repository`() =
        runBlocking {
            // Arrange
            val exercise = Exercise(
                1,
                startTime = LocalDateTime.of(2024, Month.OCTOBER, 10, 7, 24),
                15,
                ExerciseCategory.Swimming,
                5
            )

            // Act
            deleteExerciseUseCase.execute(exercise)

            // Assert
            verify(exerciseRepository, times(1)).deleteExercise(exercise)
        }
}