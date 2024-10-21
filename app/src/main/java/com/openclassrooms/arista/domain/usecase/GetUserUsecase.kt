package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repositoryInterface.UserRepositoryInterface
import com.openclassrooms.arista.domain.model.User
import javax.inject.Inject

/**
 * Use case class for getting a user from the UserRepositoryInterface.
 *
 * @param userRepositoryInterface The UserRepository implementation.
 */
class GetUserUsecase @Inject constructor(private val userRepositoryInterface: UserRepositoryInterface) {
    /**
     * Executes the use case and returns a Flow of User.
     *
     * @return A Flow emitting the User object.
     */
    suspend fun execute(): User? {
        return userRepositoryInterface.getUserById(getFirstUserId())
    }

    private fun getFirstUserId(): Long {
        // Code to retrieve the first user ID from the database
        return 1 // Replace this with the actual code to retrieve the ID from the database
    }
}