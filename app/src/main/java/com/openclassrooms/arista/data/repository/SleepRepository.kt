package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.FakeApiService
import com.openclassrooms.arista.data.dao.SleepDtoDao
import com.openclassrooms.arista.data.entity.SleepDto
import com.openclassrooms.arista.domain.model.Sleep
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SleepRepository (private val sleepDao: SleepDtoDao) {

    // Get all sleep records
    val allSleeps: Flow<List<SleepDto>> = flow {
        withContext(Dispatchers.IO) {
            sleepDao.getAllSleep()
        }
    }
}