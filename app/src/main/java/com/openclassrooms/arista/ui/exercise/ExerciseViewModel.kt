package com.openclassrooms.arista.ui.exercise

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassrooms.arista.R
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.usecase.AddNewExerciseUseCase
import com.openclassrooms.arista.domain.usecase.DeleteExerciseUseCase
import com.openclassrooms.arista.domain.usecase.GetAllExercisesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The view model class for managing exercises.
 *
 * @param getAllExercisesUseCase The use case for retrieving all exercises.
 * @param addNewExerciseUseCase The use case for adding a new exercise.
 * @param deleteExerciseUseCase The use case for deleting an exercise.
 */
@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val getAllExercisesUseCase: GetAllExercisesUseCase,
    private val addNewExerciseUseCase: AddNewExerciseUseCase,
    private val deleteExerciseUseCase: DeleteExerciseUseCase
) : ViewModel() {
    private val _exercisesFlow = MutableStateFlow<List<Exercise>>(emptyList())

    /**
     * The state flow of all exercises.
     */
    val exercisesFlow: StateFlow<List<Exercise>> = _exercisesFlow.asStateFlow()

    /**
     * Ui State of user data.
     */
    private val _uiState = MutableStateFlow(ExerciseUiState())
    val uiState: StateFlow<ExerciseUiState> = _uiState.asStateFlow()

    /**
     * update uiState if there is an error.
     */
    private fun onError(errorMessage: String) {
        Log.e(TAG, errorMessage)
        _uiState.update { currentState ->
            currentState.copy(
                error = errorMessage,

                )
        }
    }

    /**
     * Update error state to reset its value after the error message is broadcast.
     */
    fun updateErrorState(errorMessage: String) {
        val currentState = uiState.value
        val updatedState = currentState.copy(error = errorMessage)
        _uiState.value = updatedState
    }

    /**
     * Load Exercises from the database.
     */
    init {
        try {
            viewModelScope.launch {
                loadAllExercises()
            }
        } catch (e: Exception) {
            val errorMessage = (R.string.error).toString()
            onError(errorMessage)
        }
    }

    /**
     * Deletes an exercise from the database.
     *
     * @param exercise The exercise to be deleted.
     */
    suspend fun deleteExercise(exercise: Exercise) {
        try {
            deleteExerciseUseCase.execute(exercise)
            loadAllExercises()
        } catch (e: Exception) {
            val errorMessage = (R.string.error_delete_exercise).toString()
            onError(errorMessage)
        }
    }

    /**
     * Loads all exercises using the getAllExercisesUseCase and updates the exercisesFlow.
     */
    suspend fun loadAllExercises() {
        try {
            val exercises = getAllExercisesUseCase.execute()
            _exercisesFlow.value = exercises
        } catch (e: Exception) {
            val errorMessage = (R.string.error_load_exercise).toString()
            onError(errorMessage)
        }
    }

    /**
     * Adds a new exercise using the addNewExerciseUseCase and reloads all exercises.
     *
     * @param exercise The new exercise to be added.
     */
    suspend fun addNewExercise(exercise: Exercise) {
        try {
            addNewExerciseUseCase.execute(exercise)
            loadAllExercises()
        } catch (e: Exception) {
            val errorMessage = (R.string.error_add_exercise).toString()
            onError(errorMessage)
        }
    }
}
