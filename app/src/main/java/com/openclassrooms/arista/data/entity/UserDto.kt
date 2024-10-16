package com.openclassrooms.arista.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class representing a user in the database.
 *
 * @param id The id of the user. Generated automatically.
 * @param name The name of the user.
 * @param email The email of the user.
 * @param password The password of the user.
 */
@Entity(tableName = "User")
data class UserDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,

    @ColumnInfo(name = "Name")
    var name : String,

    @ColumnInfo(name = "Email")
    var email : String,

    @ColumnInfo(name="Password")
    var password : String
)
