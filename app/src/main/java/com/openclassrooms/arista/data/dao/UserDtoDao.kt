package com.openclassrooms.arista.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.openclassrooms.arista.data.entity.UserDto
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface for managing UserDto data in the local database.
 *
 */
@Dao
interface UserDtoDao {

    /**
     * Inserts a user into the database.
     *
     * @param user The user to insert.
     * @return The ID of the newly inserted user.
     */
    @Insert
    suspend fun insertUser(user: UserDto): Long

    /**
     * Retrieves all users from the database.
     *
     * @return A flow of a list of UserDto objects representing all users.
     */
    @Query("SELECT * FROM user")
    fun getAllUser(): Flow<List<UserDto>>

    /**
     * Deletes a user from the database by ID.
     *
     * @param id The ID of the user to delete.
     */
    @Query("DELETE FROM user WHERE id = :id")
    suspend fun deleteUserById(id: Long)


    /**
     * Retrieves a user from the database by ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The UserDto object representing the user, or null if not found.
     */
    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserById(id: Long): UserDto?

    /**
     * Updates a user in the database.
     *
     * @param user the user to update.
     */
    @Update
    suspend fun updateUser(user: UserDto)
}
