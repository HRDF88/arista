package com.openclassrooms.arista.di

import android.content.Context
import com.openclassrooms.arista.data.repositoryInterface.ExerciseRepositoryInterface
import com.openclassrooms.arista.data.repositoryInterface.SleepRepositoryInterface
import com.openclassrooms.arista.data.dao.ExerciseDtoDao
import com.openclassrooms.arista.data.dao.SleepDtoDao
import com.openclassrooms.arista.data.dao.UserDtoDao
import com.openclassrooms.arista.data.dataBase.AppDatabase
import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.data.repository.SleepRepository
import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.data.repositoryInterface.UserRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

/**
 * AppModule for Hilt (Dependency Injection)
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    /**
     * Provides the CoroutineScope instance for managing coroutines.
     *
     * @return The CoroutineScope instance.
     */
    @Provides
    @Singleton
    fun provideCoroutineScope(): CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    /**
     * Provides the Application Database instance.
     *
     * @param context The application context.
     * @param coroutineScope The CoroutineScope instance.
     * @return The AppDatabase instance.
     */
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        coroutineScope: CoroutineScope
    ): AppDatabase {
        return AppDatabase.getDatabase(context, coroutineScope)
    }

    /**
     * Provides the UserDtoDao instance for accessing user data in the AppDatabase.
     *
     * @param appDatabase The AppDatabase instance.
     * @return The UserDtoDao instance.
     */
    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDtoDao {
        return appDatabase.userDtoDao()
    }

    /**
     * Provides the SleepDtoDao instance for accessing sleep data in the AppDatabase.
     *
     * @param appDatabase The AppDatabase instance.
     * @return The SleepDtoDao instance.
     */
    @Provides
    fun provideSleepDao(appDatabase: AppDatabase): SleepDtoDao {
        return appDatabase.sleepDtoDao()
    }

    /**
     * Provides the ExerciseDtoDao instance for accessing exercise data in the AppDatabase.
     *
     * @param appDatabase The AppDatabase instance.
     * @return The ExerciseDtoDao instance.
     */
    @Provides
    fun provideExerciseDao(appDatabase: AppDatabase): ExerciseDtoDao {
        return appDatabase.exerciseDtoDao()
    }

    /**
     * Provides the UserRepositoryInterface instance for managing user data.
     *
     * @param userDao The UserDtoDao instance.
     * @return The UserRepositoryInterface instance.
     */
    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDtoDao): UserRepositoryInterface {
        return UserRepository(userDao)
    }

    /**
     * Provides the SleepRepositoryInterface instance for managing sleep data.
     *
     * @param sleepDtoDao The SleepDtoDao instance.
     * @return The SleepRepositoryInterface instance.
     */
    @Provides
    @Singleton
    fun provideSleepRepository(sleepDtoDao: SleepDtoDao): SleepRepositoryInterface {
        return SleepRepository(sleepDtoDao)
    }

    /**
     * Provides the ExerciseRepositoryInterface instance for managing exercise data.
     *
     * @param exerciseDtoDao The ExerciseDtoDao instance.
     * @return The ExerciseRepositoryInterface instance.
     */
    @Provides
    @Singleton
    fun provideExerciseRepository(exerciseDtoDao: ExerciseDtoDao): ExerciseRepositoryInterface {
        return ExerciseRepository(exerciseDtoDao)
    }
}