package com.openclassrooms.arista.data.repositoryInterface

import com.openclassrooms.arista.data.entity.UserDto
import com.openclassrooms.arista.domain.model.User

/**
 * Interface for managing user data.
 */
interface UserRepositoryInterface {

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The user with the specified ID, or null if not found.
     */
    suspend fun getUserById(id: Long): User?

    /**
     * Updates a user.
     *
     * @param user The user to update, represented as a UserDto object.
     */
    suspend fun updateUser(user: UserDto)
}