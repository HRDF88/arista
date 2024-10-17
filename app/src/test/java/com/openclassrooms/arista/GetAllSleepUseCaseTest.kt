package com.openclassrooms.arista

import com.openclassrooms.arista.data.repository.SleepRepository
import com.openclassrooms.arista.domain.model.Sleep
import com.openclassrooms.arista.domain.usecase.GetAllSleepsUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.time.LocalDateTime
import java.time.ZoneOffset

class GetAllSleepUseCaseTest {

    private lateinit var getAllSleepsUseCase: GetAllSleepsUseCase
    private val sleepRepository: SleepRepository = mock()

    @Before
    fun setup() {

        getAllSleepsUseCase = GetAllSleepsUseCase(sleepRepository)
    }

    @Test
    fun `when execute is called, should return the list of sleep records`() = runBlocking {
        // Arrange
        val expectedSleeps = listOf(
            Sleep(
                LocalDateTime.now().minusDays(4).atZone(ZoneOffset.UTC).toInstant()
                    .toEpochMilli(), duration = 360, 5
            )
        )
        Mockito.`when`(sleepRepository.getAllSleep()).thenReturn(expectedSleeps)

        // Act
        val result = getAllSleepsUseCase.execute()

        // Assert
        assertEquals(expectedSleeps, result)
    }

    @Test
    fun `when execute is called and the list of sleep records is empty, should return an empty list`() =
        runBlocking {
            // Arrange
            val expectedSleeps = emptyList<Sleep>()
            Mockito.`when`(sleepRepository.getAllSleep()).thenReturn(expectedSleeps)

            // Act
            val result = getAllSleepsUseCase.execute()

            // Assert
            assertEquals(expectedSleeps, result)
        }
}