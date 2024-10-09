package com.openclassrooms.arista.di

import com.openclassrooms.arista.data.dao.ExerciseDtoDao
import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.data.repository.SleepRepository
import com.openclassrooms.arista.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository {
        return UserRepository()
    }

    @Provides
    @Singleton
    fun provideSleepRepository(): SleepRepository {
        return SleepRepository()
    }

    @Provides
    @Singleton
    fun provideExerciseRepository(exerciseDtoDao: ExerciseDtoDao): ExerciseRepository {
        return ExerciseRepository(exerciseDtoDao)
    }
}