package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repositoryInterface.SleepRepositoryInterface
import com.openclassrooms.arista.domain.model.Sleep
import javax.inject.Inject

/**
 * Use case class responsible for executing the business logic related to retrieving all Sleep records.
 * This UseCase decouples and isolates the retrieval logic from the ViewModel, following the principles
 * of Clean Architecture.
 *
 * @param sleepRepositoryInterface The interface of repository that provides access to Sleep data.
 */
class GetAllSleepsUseCase @Inject constructor(private val sleepRepositoryInterface: SleepRepositoryInterface) {
    suspend fun execute(): List<Sleep> {
        return sleepRepositoryInterface.getAllSleep()
    }
}