package com.openclassrooms.arista.ui.sleep

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassrooms.arista.R
import com.openclassrooms.arista.domain.model.Sleep
import com.openclassrooms.arista.domain.usecase.GetAllSleepsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *SleepViewModel is responsible for preparing and managing the data for the {@link SleepFragment}.
 * It communicates with the SleepUseCase to fetch list of Sleep and provides
 * utility methods related to the notes UI.
 */
@HiltViewModel
class SleepViewModel @Inject constructor(private val getAllSleepsUseCase: GetAllSleepsUseCase) :
    ViewModel() {
    private val _sleeps = MutableStateFlow<List<Sleep>>(emptyList())
    val sleeps: StateFlow<List<Sleep>> = _sleeps.asStateFlow()

    /**
     * Ui State of user data.
     */
    private val _uiState = MutableStateFlow(SleepUiState())
    val uiState : StateFlow<SleepUiState> = _uiState.asStateFlow()


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
     * Catch the list of Sleep DTO and transform each element of the list to Sleep Object.
     * @return list of Sleep.
     */
    fun fetchSleeps() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val sleepListDto = getAllSleepsUseCase.execute()
                val sleepList: List<Sleep> = sleepListDto
                    .map { sleepDto ->
                        Sleep(
                            startTime = sleepDto.startTime,
                            duration = sleepDto.duration,
                            quality = sleepDto.quality
                        )
                    }
                _sleeps.value = sleepList.toList()
            } catch (e: Exception) {
                val errorMessage = (R.string.error).toString()
                onError(errorMessage)
            }
        }
    }
}
