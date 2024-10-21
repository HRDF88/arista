package com.openclassrooms.arista.domain.model

import com.openclassrooms.arista.data.entity.UserDto

/**
 * Data class representing a user in the application.
 */
data class User(
    var name: String,
    var email: String,
    val id: Long,
    val password: String
) {
    companion object {
        /**
         * Creates a User object from a UserDto object.
         *
         * @param userDto The UserDto object to convert.
         * @return A User object with data mapped from the UserDto object.
         */
        fun fromDto(userDto: UserDto): User {
            return User(
                name = userDto.name,
                email = userDto.email,
                id = userDto.id,
                password = userDto.password
            )
        }
    }
}
