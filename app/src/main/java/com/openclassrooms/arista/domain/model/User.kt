package com.openclassrooms.arista.domain.model

/**
 * Data class representing a user in the application.
 */
data class User(var name: String, var email: String, val id: Long, val password: String)