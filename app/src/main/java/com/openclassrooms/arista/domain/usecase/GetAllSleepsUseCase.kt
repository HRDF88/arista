package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.SleepRepository
import com.openclassrooms.arista.domain.model.Sleep
import javax.inject.Inject

/**
 * Use case class responsible for executing the business logic related to retrieving all Sleep records.
 * This UseCase decouples and isolates the retrieval logic from the ViewModel, following the principles
 * of Clean Architecture.
 *
 * @param sleepRepository The repository that provides access to Sleep data.
 */
class GetAllSleepsUseCase @Inject constructor(private val sleepRepository: SleepRepository) {
    suspend fun execute(): List<Sleep> {
        return sleepRepository.getAllSleep()
    }
}