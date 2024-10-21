package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.UserDtoDao
import com.openclassrooms.arista.data.entity.UserDto
import com.openclassrooms.arista.data.repositoryInterface.UserRepositoryInterface
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * The repository class for managing user data.
 *
 * @param userDao The data access object for UserDto.
 */
class UserRepository @Inject constructor(private val userDao: UserDtoDao) : UserRepositoryInterface {

    /**
     * The flow of all users retrieved from the database.
     */
    val user: Flow<List<User>> =
        userDao.getAllUser().map { userList ->
            userList.map { userDto ->
                User.fromDto(userDto)
            }
        }

    /**
     * Retrieves a user from the database based on the specified ID.
     *
     * @param id the ID of the user to fetch.
     * @return the user object if found, or null if no user exists with the given ID.
     */
    override suspend fun getUserById(id: Long): User? {
        return userDao.getUserById(id)?.let { userDto ->
            User.fromDto(userDto)
        }
    }

    /**
     * Updates a user in the database.
     *
     * @param user the user object with updated data.
     */
    override suspend fun updateUser(user: UserDto) {
        userDao.updateUser(user)
    }
}

