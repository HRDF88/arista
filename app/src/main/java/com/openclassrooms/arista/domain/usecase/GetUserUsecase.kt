package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.entity.UserDto
import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Use case class for getting a user from the UserRepository.
 *
 * @param userRepository The UserRepository implementation.
 */
class GetUserUsecase @Inject constructor(private val userRepository: UserRepository) {
    /**
     * Executes the use case and returns a Flow of User.
     *
     * @return A Flow emitting the User object.
     */
    fun execute(): Flow<User> {
        return userRepository.user.map { userList ->
            userList.first()
        }
    }
}