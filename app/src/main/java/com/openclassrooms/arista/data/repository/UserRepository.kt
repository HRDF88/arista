package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.UserDtoDao
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * The repository class for managing user data.
 *
 * @param userDao The data access object for UserDto.
 */
class UserRepository @Inject constructor(private val userDao: UserDtoDao) {

    /**
     * The flow of all users retrieved from the database.
     */
    val user: Flow<List<User>> = userDao.getAllUser().map { userList ->
        userList.map { userDto ->
            User(
                name = userDto.name,
                email = userDto.email,
                id = userDto.id,
                password = userDto.password
            )
        }
    }
}

