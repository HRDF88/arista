package com.openclassrooms.arista.ui.user

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassrooms.arista.R
import com.openclassrooms.arista.domain.model.User
import com.openclassrooms.arista.domain.usecase.GetUserUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The view model class for managing user data.
 *
 * @param getUserUsecase The use case for retrieving user data.
 */
@HiltViewModel
class UserDataViewModel @Inject constructor(private val getUserUsecase: GetUserUsecase) :
    ViewModel() {
    private val _userFlow = MutableStateFlow<User?>(null)

    /**
     * The state flow of the user data.
     */
    val userFlow: StateFlow<User?> = _userFlow.asStateFlow()

    /**
     * Ui State of user data.
     */
    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()


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

    init {
        loadUserData()
    }

    /**
     *  Loads the user data from the repository and updates.
     */
    private fun loadUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userDto = getUserUsecase.execute().firstOrNull()
                val user = userDto?.let { dto ->
                    User(
                        name = dto.name,
                        email = dto.email,
                        id = dto.id,
                        password = dto.password
                    )
                }
                _userFlow.value = user
            } catch (e: Exception) {
                val errorMessage = (R.string.error).toString()
                onError(errorMessage)
            }
        }
    }
}
